import { memo, useMemo } from 'react';
import ReactMarkdown from 'react-markdown';
import remarkGfm from 'remark-gfm';
import remarkMath from 'remark-math';
import rehypeHighlight from 'rehype-highlight';
import rehypeKatex from 'rehype-katex';
import { getParentPath } from '../utils/tree.js';

function Viewer({ content, currentFile, loading }) {
  const dirPath = useMemo(
    () => (currentFile ? getParentPath(currentFile) : ''),
    [currentFile]
  );

  const components = useMemo(() => ({
    img: ({ src, alt, ...props }) => {
      if (src && !src.startsWith('http') && !src.startsWith('data:')) {
        const resolvedPath = dirPath ? `${dirPath}/${src}` : src;
        return (
          <img
            src={`/api/image?path=${encodeURIComponent(resolvedPath)}`}
            alt={alt || ''}
            className="md-image"
            loading="lazy"
            {...props}
          />
        );
      }
      return <img src={src} alt={alt || ''} className="md-image" loading="lazy" {...props} />;
    },
    table: ({ children, ...props }) => (
      <div className="table-wrapper">
        <table {...props}>{children}</table>
      </div>
    ),
    a: ({ href, children, ...props }) => (
      <a href={href} target="_blank" rel="noopener noreferrer" {...props}>{children}</a>
    ),
    code: ({ children, className, node, ...props }) => {
      const isInline = !className;
      if (isInline) {
        return <code className="inline-code" {...props}>{children}</code>;
      }
      return <code className={className} {...props}>{children}</code>;
    },
  }), [dirPath]);

  if (!currentFile) {
    return (
      <div className="viewer-empty">
        <div className="viewer-empty-icon">⬡</div>
        <h2>Samu CyberLocker</h2>
        <p>Seleziona un file dalla sidebar per iniziare.</p>
        <p className="viewer-empty-hint">
          Usa la barra di ricerca per trovare lezioni specifiche.
        </p>
      </div>
    );
  }

  if (loading) {
    return (
      <div className="viewer-loading">
        <div className="loading-spinner" />
        <p>Caricamento...</p>
      </div>
    );
  }

  return (
    <article className="viewer-content markdown-body">
      <ReactMarkdown
        remarkPlugins={[remarkGfm, remarkMath]}
        rehypePlugins={[rehypeKatex, rehypeHighlight]}
        components={components}
      >
        {content}
      </ReactMarkdown>
    </article>
  );
}

export default memo(Viewer);

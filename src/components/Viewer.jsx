import { memo, useMemo } from "react";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import remarkMath from "remark-math";
import remarkCallouts from "../utils/remarkCallouts.js";
import rehypeHighlight from "rehype-highlight";
import rehypeKatex from "rehype-katex";
import { getParentPath } from "../utils/tree.js";

function Viewer({ content, currentFile, loading }) {
  const dirPath = useMemo(() => (currentFile ? getParentPath(currentFile) : ""), [currentFile]);

  const components = useMemo(
    () => ({
      img: ({ src, alt, ...props }) => {
        if (src && !src.startsWith("http") && !src.startsWith("data:")) {
          // Decode %20 etc. first, then resolve relative path (../)
          const decoded = decodeURIComponent(src);
          const parts = dirPath ? `${dirPath}/${decoded}`.split("/") : decoded.split("/");
          const resolved = [];
          for (const p of parts) {
            if (p === "..") resolved.pop();
            else if (p && p !== ".") resolved.push(p);
          }
          const resolvedPath = resolved.join("/");
          const base = import.meta.env.BASE_URL;
          return (
            <img
              src={`${base}lessons/${encodeURI(resolvedPath)}`}
              alt={alt || ""}
              className="md-image"
              loading="lazy"
              {...props}
            />
          );
        }
        return <img src={src} alt={alt || ""} className="md-image" loading="lazy" {...props} />;
      },
      table: ({ children, ...props }) => (
        <div className="table-wrapper">
          <table {...props}>{children}</table>
        </div>
      ),
      a: ({ href, children, ...props }) => (
        <a href={href} target="_blank" rel="noopener noreferrer" {...props}>
          {children}
        </a>
      ),
      code: ({ children, className, node, ...props }) => {
        const isInline = !className;
        if (isInline) {
          return (
            <code className="inline-code" {...props}>
              {children}
            </code>
          );
        }
        return (
          <code className={className} {...props}>
            {children}
          </code>
        );
      },
    }),
    [dirPath],
  );

  if (!currentFile) {
    return (
      <div className="viewer-empty">
        <div className="viewer-empty-icon">
          <svg width="72" height="72" viewBox="0 0 72 72" fill="none">
            <polygon
              points="36,2 66,19 66,53 36,70 6,53 6,19"
              stroke="currentColor"
              strokeWidth="1.5"
              fill="currentColor"
              fillOpacity="0.04"
            />
            <polygon
              points="36,10 58,23 58,49 36,62 14,49 14,23"
              stroke="currentColor"
              strokeWidth="1"
              fill="currentColor"
              fillOpacity="0.06"
              opacity="0.6"
            />
            <polygon
              points="36,18 50,27 50,45 36,54 22,45 22,27"
              stroke="currentColor"
              strokeWidth="0.8"
              fill="currentColor"
              fillOpacity="0.08"
              opacity="0.3"
            />
            <circle cx="36" cy="36" r="3" fill="currentColor" opacity="0.8" />
          </svg>
        </div>
        <h2>Samu CyberLocker</h2>
        <p className="viewer-empty-subtitle">Knowledge Vault System</p>
        <div className="viewer-empty-divider"></div>
        <p className="viewer-empty-hint">Seleziona un file dalla sidebar per iniziare</p>
        <p className="viewer-empty-hint">
          <kbd>Ctrl</kbd> + <kbd>K</kbd> per cercare
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
        remarkPlugins={[remarkGfm, remarkMath, remarkCallouts]}
        rehypePlugins={[rehypeKatex, rehypeHighlight]}
        components={components}>
        {content}
      </ReactMarkdown>
    </article>
  );
}

export default memo(Viewer);

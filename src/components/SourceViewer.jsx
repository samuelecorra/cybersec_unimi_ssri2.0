import { memo, useEffect, useMemo, useState } from 'react';
import hljs from 'highlight.js/lib/core';
import c from 'highlight.js/lib/languages/c';
import java from 'highlight.js/lib/languages/java';
import { getSourceFileType } from '../utils/fileTypes.js';

hljs.registerLanguage('c', c);
hljs.registerLanguage('java', java);

function highlightSource(content, language) {
  if (!language || !content) return null;
  try {
    return hljs.highlight(content, { language, ignoreIllegals: true }).value;
  } catch {
    return null;
  }
}

function SourceViewer({ content, currentFile, loading }) {
  const [copied, setCopied] = useState(false);
  const [wrapLines, setWrapLines] = useState(false);
  const sourceType = useMemo(() => getSourceFileType(currentFile), [currentFile]);
  const highlighted = useMemo(
    () => highlightSource(content, sourceType?.language),
    [content, sourceType?.language],
  );
  const lineCount = useMemo(
    () => (content.length === 0 ? 0 : content.split('\n').length),
    [content],
  );
  const byteCount = useMemo(() => new TextEncoder().encode(content).length, [content]);

  useEffect(() => {
    setCopied(false);
    setWrapLines(false);
  }, [currentFile]);

  useEffect(() => {
    if (!copied) return undefined;
    const timer = window.setTimeout(() => setCopied(false), 1800);
    return () => window.clearTimeout(timer);
  }, [copied]);

  const copySource = async () => {
    try {
      await navigator.clipboard.writeText(content);
      setCopied(true);
    } catch {
      setCopied(false);
    }
  };

  if (loading) {
    return (
      <div className="viewer-loading">
        <div className="loading-spinner" />
        <p>Caricamento...</p>
      </div>
    );
  }

  const lineNumbers = Array.from({ length: lineCount }, (_, index) => index + 1);
  const fileName = currentFile.split('/').pop();

  return (
    <section className={`source-viewer${wrapLines ? ' source-viewer--wrap' : ''}`}>
      <div className="source-toolbar">
        <div className="source-file-info">
          <span className="source-language-badge">{sourceType?.label ?? 'Testo'}</span>
          <span className="source-file-name" title={currentFile}>{fileName}</span>
          <span className="source-file-stats">
            {lineCount} {lineCount === 1 ? 'riga' : 'righe'} · {byteCount.toLocaleString('it-IT')} byte
          </span>
        </div>
        <div className="source-actions">
          <button
            type="button"
            className={`source-action${wrapLines ? ' active' : ''}`}
            onClick={() => setWrapLines(value => !value)}
            aria-pressed={wrapLines}
            title={wrapLines ? 'Disattiva ritorno a capo' : 'Attiva ritorno a capo'}
          >
            ↩ A capo
          </button>
          <button
            type="button"
            className={`source-action${copied ? ' success' : ''}`}
            onClick={copySource}
            disabled={!content}
          >
            {copied ? '✓ Copiato' : 'Copia'}
          </button>
        </div>
      </div>

      <div className="source-scroll" tabIndex="0" aria-label={`Sorgente ${fileName}`}>
        {lineCount > 0 && (
          <div className="source-line-numbers" aria-hidden="true">
            {lineNumbers.map(line => <span key={line}>{line}</span>)}
          </div>
        )}
        <pre className="source-code hljs">
          {highlighted !== null ? (
            <code
              className={`language-${sourceType.language}`}
              dangerouslySetInnerHTML={{ __html: highlighted }}
            />
          ) : (
            <code>{content}</code>
          )}
        </pre>
      </div>
    </section>
  );
}

export default memo(SourceViewer);


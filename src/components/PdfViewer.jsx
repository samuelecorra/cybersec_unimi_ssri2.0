import { memo, useMemo } from 'react';

function PdfViewer({ filePath }) {
  const url = useMemo(() => {
    const base = import.meta.env.BASE_URL;
    return `${base}lessons/${filePath}`;
  }, [filePath]);

  return (
    <div className="pdf-viewer">
      <div className="pdf-viewer-toolbar">
        <span>PDF originale</span>
        <a href={url} target="_blank" rel="noreferrer">Apri in una nuova scheda</a>
      </div>
      <iframe
        className="pdf-viewer-frame"
        src={url}
        title={`Documento PDF: ${filePath.split('/').pop()}`}
      />
    </div>
  );
}

export default memo(PdfViewer);

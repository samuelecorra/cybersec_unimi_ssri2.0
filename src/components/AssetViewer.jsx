import { memo } from 'react';
import { encodePathSegments, getMediaFileType } from '../utils/fileTypes.js';

function AssetViewer({ currentFile }) {
  const mediaType = getMediaFileType(currentFile);
  const fileName = currentFile.split('/').pop();
  const sourceUrl = `${import.meta.env.BASE_URL}lessons/${encodePathSegments(currentFile)}`;

  return (
    <section className="asset-viewer">
      <div className="source-toolbar">
        <div className="source-file-info">
          <span className="source-language-badge">{mediaType?.label ?? 'Risorsa'}</span>
          <span className="source-file-name" title={currentFile}>{fileName}</span>
        </div>
        <a className="source-action asset-open-link" href={sourceUrl} target="_blank" rel="noreferrer">
          Apri originale
        </a>
      </div>

      <div className="asset-content">
        {mediaType?.category === 'image' ? (
          <img src={sourceUrl} alt={fileName} className="asset-image" />
        ) : mediaType?.category === 'audio' ? (
          <audio src={sourceUrl} controls preload="metadata" className="asset-audio">
            Il browser non supporta la riproduzione audio.
          </audio>
        ) : null}
      </div>
    </section>
  );
}

export default memo(AssetViewer);

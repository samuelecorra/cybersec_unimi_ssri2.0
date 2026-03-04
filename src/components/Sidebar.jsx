import { memo } from 'react';
import FileTree from './FileTree.jsx';

function Sidebar({
  tree,
  currentFile,
  expandedDirs,
  onToggleDir,
  onSelectFile,
  searchResults,
  searchQuery,
}) {
  return (
    <aside className="sidebar">
      {searchResults ? (
        <div className="search-results">
          <div className="search-results-header">
            {searchResults.length} risultat{searchResults.length === 1 ? 'o' : 'i'} per "{searchQuery}"
          </div>
          <div className="search-results-list">
            {searchResults.length === 0 && (
              <div className="search-empty">Nessun risultato trovato.</div>
            )}
            {searchResults.map(item => (
              <button
                key={item.path}
                className={`search-result-item ${currentFile === item.path ? 'active' : ''}`}
                onClick={() => onSelectFile(item.path)}
              >
                <span className="search-result-name">
                  {item.path.split('/').pop()}
                </span>
                <span className="search-result-path">{item.path}</span>
                {item.headings.length > 0 && (
                  <span className="search-result-headings">
                    {item.headings.slice(0, 3).join(' · ')}
                  </span>
                )}
              </button>
            ))}
          </div>
        </div>
      ) : (
        <div className="file-tree-container">
          <div className="tree-header">📂 Filesystem</div>
          <FileTree
            nodes={tree}
            currentFile={currentFile}
            expandedDirs={expandedDirs}
            onToggleDir={onToggleDir}
            onSelectFile={onSelectFile}
            depth={0}
          />
        </div>
      )}
    </aside>
  );
}

export default memo(Sidebar);

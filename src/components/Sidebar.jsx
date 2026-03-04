import { memo } from 'react';
import FileTree from './FileTree.jsx';

const SearchResultIcon = () => (
  <svg width="12" height="12" viewBox="0 0 12 12" fill="none" className="search-result-icon">
    <path d="M1 2C1 1.45 1.45 1 2 1H7L11 5V10C11 10.55 10.55 11 10 11H2C1.45 11 1 10.55 1 10V2Z" stroke="currentColor" strokeWidth="1" />
    <path d="M7 1V5H11" stroke="currentColor" strokeWidth="1" />
  </svg>
);

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
            <span className="search-results-label">Risultati</span>
            <span className="search-results-badge">{searchResults.length}</span>
            <span className="search-results-query">per "{searchQuery}"</span>
          </div>
          <div className="search-results-list">
            {searchResults.length === 0 && (
              <div className="search-empty">
                <span className="search-empty-icon">∅</span>
                <span>Nessun risultato trovato.</span>
              </div>
            )}
            {searchResults.map(item => (
              <button
                key={item.path}
                className={`search-result-item ${currentFile === item.path ? 'active' : ''}`}
                onClick={() => onSelectFile(item.path)}
              >
                <div className="search-result-top">
                  <SearchResultIcon />
                  <span className="search-result-name">
                    {item.path.split('/').pop().replace(/\.md$/, '')}
                  </span>
                </div>
                <span className="search-result-path">{item.path}</span>
                {item.headings.length > 0 && (
                  <span className="search-result-headings">
                    {item.headings.slice(0, 3).join(' → ')}
                  </span>
                )}
              </button>
            ))}
          </div>
        </div>
      ) : (
        <div className="file-tree-container">
          <div className="tree-header">
            <span className="tree-header-icon">
              <svg width="14" height="14" viewBox="0 0 14 14" fill="none">
                <rect x="1" y="1" width="5" height="5" rx="1" stroke="currentColor" strokeWidth="1.2" />
                <rect x="8" y="1" width="5" height="5" rx="1" stroke="currentColor" strokeWidth="1.2" opacity="0.5" />
                <rect x="1" y="8" width="5" height="5" rx="1" stroke="currentColor" strokeWidth="1.2" opacity="0.5" />
                <rect x="8" y="8" width="5" height="5" rx="1" stroke="currentColor" strokeWidth="1.2" opacity="0.3" />
              </svg>
            </span>
            <span>Filesystem</span>
          </div>
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

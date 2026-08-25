import { memo } from 'react';
import FileTree from './FileTree.jsx';

const SearchResultIcon = ({ active }) => (
  <svg
    width="12"
    height="12"
    viewBox="0 0 12 12"
    fill="none"
    className={`shrink-0 ${active ? 'text-neon-cyan' : 'text-text-muted'}`}
  >
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
    <aside className="sidebar relative flex w-[var(--sidebar-width)] min-w-[var(--sidebar-width)] flex-col overflow-hidden border-r border-glass-border bg-glass-bg backdrop-blur-[16px] max-[768px]:absolute max-[768px]:inset-y-0 max-[768px]:left-0 max-[768px]:z-[200] max-[768px]:max-w-[320px] max-[768px]:shadow-[4px_0_30px_rgba(0,0,0,0.55)]">
      {searchResults ? (
        <div className="flex flex-1 flex-col overflow-hidden">
          <div className="flex items-center gap-2 border-b border-glass-border px-4 py-3 font-display text-[0.7rem] font-semibold uppercase tracking-[2px] text-neon-yellow [text-shadow:0_0_12px_rgba(255,215,0,0.35)]">
            <span className="shrink-0">Risultati</span>
            <span className="rounded-[10px] border border-neon-yellow/25 bg-neon-yellow/12 px-2 py-px font-mono text-[0.7rem] normal-case tracking-normal text-neon-yellow">
              {searchResults.length}
            </span>
            <span className="truncate font-body text-[0.8rem] font-normal normal-case tracking-normal text-text-muted [text-shadow:none]">
              per "{searchQuery}"
            </span>
          </div>
          <div className="neon-scrollbar flex-1 overflow-y-auto py-1">
            {searchResults.length === 0 && (
              <div className="flex flex-col items-center gap-2 px-4 py-8 text-center text-text-muted">
                <span className="text-2xl opacity-40">∅</span>
                <span>Nessun risultato trovato.</span>
              </div>
            )}
            {searchResults.map(item => {
              const isActive = currentFile === item.path;
              return (
                <button
                  key={item.path}
                  className={`flex w-full flex-col gap-[3px] border-l-2 bg-transparent px-4 py-2.5 text-left font-mono text-[0.8rem] text-text-primary transition-all duration-200 ease-out hover:bg-[var(--bg-hover)] ${
                    isActive
                      ? 'border-l-neon-cyan bg-[linear-gradient(90deg,rgba(255,191,0,0.08),transparent)]'
                      : 'border-l-transparent'
                  }`}
                  onClick={() => onSelectFile(item.path)}
                >
                  <div className="flex items-center gap-1.5">
                    <SearchResultIcon active={isActive} />
                    <span className="font-semibold text-neon-cyan">
                      {item.path.split('/').pop().replace(/\.md$/, '')}
                    </span>
                  </div>
                  <span className="truncate text-[0.7rem] text-text-muted">{item.path}</span>
                  {item.headings.length > 0 && (
                    <span className="truncate text-[0.7rem] text-neon-lime opacity-70">
                      {item.headings.slice(0, 3).join(' → ')}
                    </span>
                  )}
                </button>
              );
            })}
          </div>
        </div>
      ) : (
        <div className="flex flex-1 flex-col overflow-hidden">
          <div className="flex items-center gap-2 border-b border-glass-border px-4 py-3 font-display text-[0.7rem] font-semibold uppercase tracking-[3px] text-neon-cyan [text-shadow:0_0_12px_var(--glow-cyan)]">
            <span className="flex items-center text-neon-cyan drop-shadow-[0_0_4px_var(--glow-cyan)]">
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

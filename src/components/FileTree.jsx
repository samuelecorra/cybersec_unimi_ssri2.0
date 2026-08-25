import { memo } from 'react';
import { formatSize } from '../utils/tree.js';
import { getDisplayFileType } from '../utils/fileTypes.js';

/* ─── SVG Icons ─── */
const ChevronIcon = ({ expanded }) => (
  <svg
    className={`shrink-0 text-text-muted transition-[transform,color] duration-150 ease-out ${expanded ? 'rotate-90 text-neon-lime' : ''}`}
    width="10"
    height="10"
    viewBox="0 0 10 10"
    fill="none"
  >
    <path d="M3 1.5L7 5L3 8.5" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

const FolderIcon = () => (
  <svg
    className="shrink-0 text-neon-lime transition-[color,filter] duration-200 ease-out group-hover:drop-shadow-[0_0_6px_var(--glow-lime)]"
    width="16"
    height="16"
    viewBox="0 0 16 16"
    fill="none"
  >
    {/* open/closed variant kept visually identical to the previous version;
        FileTree only ever renders the closed glyph here (see history) */}
    <rect x="1.5" y="3" width="12" height="9.5" rx="1" fill="currentColor" opacity="0.1" stroke="currentColor" strokeWidth="1" />
    <path d="M1.5 4C1.5 3.45 1.95 3 2.5 3H5.5L7 4.5H12.5C13.05 4.5 13.5 4.95 13.5 5.5" stroke="currentColor" strokeWidth="1" />
  </svg>
);

const FolderIconOpen = () => (
  <svg
    className="shrink-0 text-neon-lime transition-[color,filter] duration-200 ease-out group-hover:drop-shadow-[0_0_6px_var(--glow-lime)]"
    width="16"
    height="16"
    viewBox="0 0 16 16"
    fill="none"
  >
    <path d="M1.5 3.5C1.5 2.95 1.95 2.5 2.5 2.5H5.8L7.3 4H12.5C13.05 4 13.5 4.45 13.5 5V5.5H3.5L1.5 12V3.5Z" fill="currentColor" opacity="0.15" />
    <path d="M2.5 5.5H14L12 12.5H0.5L2.5 5.5Z" fill="currentColor" opacity="0.3" />
    <path d="M1.5 3.5C1.5 2.95 1.95 2.5 2.5 2.5H5.8L7.3 4H12.5C13.05 4 13.5 4.45 13.5 5V5.5H3.5L1.5 12V3.5Z" stroke="currentColor" strokeWidth="1" />
    <path d="M2.5 5.5H14L12 12.5H0.5L2.5 5.5Z" stroke="currentColor" strokeWidth="1" />
  </svg>
);

const FileIcon = ({ active, source }) => (
  <svg
    className={`shrink-0 transition-[color,filter] duration-200 ease-out ${
      active ? 'text-neon-cyan drop-shadow-[0_0_4px_var(--glow-cyan)]' : 'text-text-muted group-hover:text-text-secondary'
    }`}
    width="14"
    height="16"
    viewBox="0 0 14 16"
    fill="none"
  >
    <path d="M1 2C1 1.45 1.45 1 2 1H8.5L13 5.5V14C13 14.55 12.55 15 12 15H2C1.45 15 1 14.55 1 14V2Z" fill="currentColor" opacity={active ? '0.15' : '0.05'} stroke="currentColor" strokeWidth="1" />
    <path d="M8.5 1V5.5H13" stroke="currentColor" strokeWidth="1" />
    {source ? (
      <>
        <path d="M5.3 8L3.6 10L5.3 12" stroke="currentColor" strokeWidth="0.8" strokeLinecap="round" strokeLinejoin="round" />
        <path d="M8.7 8L10.4 10L8.7 12" stroke="currentColor" strokeWidth="0.8" strokeLinecap="round" strokeLinejoin="round" />
      </>
    ) : (
      <>
        <line x1="3.5" y1="8.5" x2="10.5" y2="8.5" stroke="currentColor" strokeWidth="0.7" opacity="0.4" />
        <line x1="3.5" y1="10.5" x2="9" y2="10.5" stroke="currentColor" strokeWidth="0.7" opacity="0.4" />
        <line x1="3.5" y1="12.5" x2="7" y2="12.5" stroke="currentColor" strokeWidth="0.7" opacity="0.4" />
      </>
    )}
  </svg>
);

const treeBtnBase =
  'tree-indent group flex w-full items-center gap-1.5 border-l-2 bg-transparent py-1 pr-3 text-left font-mono text-[0.8rem] text-text-primary transition-all duration-200 ease-out hover:bg-[var(--bg-hover)]';

function FileTree({ nodes, currentFile, expandedDirs, onToggleDir, onSelectFile, depth }) {
  return (
    <ul
      className={`list-none ${depth === 0 ? 'neon-scrollbar flex-1 overflow-y-auto py-2' : ''}`}
      style={{ '--depth': depth }}
    >
      {nodes.map(node => {
        if (node.type === 'dir') {
          const isExpanded = expandedDirs[node.path] ?? false;
          const fileCount = countFiles(node);
          return (
            <li key={node.path}>
              <button
                className={`${treeBtnBase} border-l-transparent font-semibold text-neon-lime hover:[text-shadow:0_0_10px_var(--glow-lime)]`}
                onClick={() => onToggleDir(node.path)}
              >
                <ChevronIcon expanded={isExpanded} />
                {isExpanded ? <FolderIconOpen /> : <FolderIcon />}
                <span className="tree-name">{node.name}</span>
                {node.children && (
                  <span className="ml-auto shrink-0 text-[0.65rem] text-neon-cyan">
                    <span className="rounded-[10px] border border-neon-cyan/15 bg-neon-cyan/8 px-[7px] py-px font-mono">
                      {fileCount}
                    </span>
                  </span>
                )}
              </button>
              {isExpanded && node.children && (
                <div className="tree-dir-children relative">
                  <FileTree
                    nodes={node.children}
                    currentFile={currentFile}
                    expandedDirs={expandedDirs}
                    onToggleDir={onToggleDir}
                    onSelectFile={onSelectFile}
                    depth={depth + 1}
                  />
                </div>
              )}
            </li>
          );
        }

        const isActive = currentFile === node.path;
        const displayType = getDisplayFileType(node.path);
        return (
          <li key={node.path}>
            <button
              className={`${treeBtnBase} ${
                isActive
                  ? 'border-l-neon-cyan bg-[linear-gradient(90deg,rgba(255,191,0,0.1),transparent)] text-neon-cyan [text-shadow:0_0_8px_var(--glow-cyan)]'
                  : 'border-l-transparent'
              }`}
              onClick={() => onSelectFile(node.path)}
              title={node.path}
            >
              <FileIcon active={isActive} source={displayType?.category === 'source' || displayType?.category === 'support'} />
              <span className="tree-name flex-1 truncate">{node.name.replace(/\.(md|html|pdf)$/, '')}</span>
              {displayType && (
                <span className="shrink-0 rounded-[3px] border border-neon-lime/20 px-[5px] py-px font-mono text-[0.58rem] uppercase leading-[1.4] tracking-[0.04em] text-neon-lime">
                  {displayType.label}
                </span>
              )}
              {node.size != null && (
                <span className="ml-auto shrink-0 font-mono text-[0.65rem] text-text-muted">{formatSize(node.size)}</span>
              )}
            </button>
          </li>
        );
      })}
    </ul>
  );
}

function countFiles(node) {
  if (node.type === 'file' || node.type === 'web-lesson') return 1;
  if (!node.children) return 0;
  return node.children.reduce((sum, child) => sum + countFiles(child), 0);
}

export default memo(FileTree);

import { memo } from 'react';
import { formatSize } from '../utils/tree.js';

/* ─── SVG Icons ─── */
const ChevronIcon = ({ expanded }) => (
  <svg className={`tree-chevron ${expanded ? 'expanded' : ''}`} width="10" height="10" viewBox="0 0 10 10" fill="none">
    <path d="M3 1.5L7 5L3 8.5" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

const FolderIcon = ({ open }) => (
  <svg className="tree-icon tree-icon--folder" width="16" height="16" viewBox="0 0 16 16" fill="none">
    {open ? (
      <>
        <path d="M1.5 3.5C1.5 2.95 1.95 2.5 2.5 2.5H5.8L7.3 4H12.5C13.05 4 13.5 4.45 13.5 5V5.5H3.5L1.5 12V3.5Z" fill="currentColor" opacity="0.15" />
        <path d="M2.5 5.5H14L12 12.5H0.5L2.5 5.5Z" fill="currentColor" opacity="0.3" />
        <path d="M1.5 3.5C1.5 2.95 1.95 2.5 2.5 2.5H5.8L7.3 4H12.5C13.05 4 13.5 4.45 13.5 5V5.5H3.5L1.5 12V3.5Z" stroke="currentColor" strokeWidth="1" />
        <path d="M2.5 5.5H14L12 12.5H0.5L2.5 5.5Z" stroke="currentColor" strokeWidth="1" />
      </>
    ) : (
      <>
        <rect x="1.5" y="3" width="12" height="9.5" rx="1" fill="currentColor" opacity="0.1" stroke="currentColor" strokeWidth="1" />
        <path d="M1.5 4C1.5 3.45 1.95 3 2.5 3H5.5L7 4.5H12.5C13.05 4.5 13.5 4.95 13.5 5.5" stroke="currentColor" strokeWidth="1" />
      </>
    )}
  </svg>
);

const FileIcon = ({ active }) => (
  <svg className={`tree-icon tree-icon--file ${active ? 'active' : ''}`} width="14" height="16" viewBox="0 0 14 16" fill="none">
    <path d="M1 2C1 1.45 1.45 1 2 1H8.5L13 5.5V14C13 14.55 12.55 15 12 15H2C1.45 15 1 14.55 1 14V2Z" fill="currentColor" opacity={active ? '0.15' : '0.05'} stroke="currentColor" strokeWidth="1" />
    <path d="M8.5 1V5.5H13" stroke="currentColor" strokeWidth="1" />
    <line x1="3.5" y1="8.5" x2="10.5" y2="8.5" stroke="currentColor" strokeWidth="0.7" opacity="0.4" />
    <line x1="3.5" y1="10.5" x2="9" y2="10.5" stroke="currentColor" strokeWidth="0.7" opacity="0.4" />
    <line x1="3.5" y1="12.5" x2="7" y2="12.5" stroke="currentColor" strokeWidth="0.7" opacity="0.4" />
  </svg>
);

function FileTree({ nodes, currentFile, expandedDirs, onToggleDir, onSelectFile, depth }) {
  return (
    <ul className="tree-list" style={{ '--depth': depth }}>
      {nodes.map(node => {
        if (node.type === 'dir') {
          const isExpanded = expandedDirs[node.path] ?? false;
          const fileCount = countFiles(node);
          return (
            <li key={node.path} className="tree-dir">
              <button
                className={`tree-dir-btn ${isExpanded ? 'expanded' : ''}`}
                onClick={() => onToggleDir(node.path)}
              >
                <ChevronIcon expanded={isExpanded} />
                <FolderIcon open={isExpanded} />
                <span className="tree-name">{node.name}</span>
                {node.children && (
                  <span className="tree-count">
                    <span className="tree-count-num">{fileCount}</span>
                  </span>
                )}
              </button>
              {isExpanded && node.children && (
                <div className="tree-dir-children">
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
        return (
          <li key={node.path} className="tree-file">
            <button
              className={`tree-file-btn ${isActive ? 'active' : ''}`}
              onClick={() => onSelectFile(node.path)}
              title={node.path}
            >
              <FileIcon active={isActive} />
              <span className="tree-name">{node.name.replace(/\.md$/, '')}</span>
              {node.size != null && (
                <span className="tree-size">{formatSize(node.size)}</span>
              )}
            </button>
          </li>
        );
      })}
    </ul>
  );
}

function countFiles(node) {
  if (node.type === 'file') return 1;
  if (!node.children) return 0;
  return node.children.reduce((sum, child) => sum + countFiles(child), 0);
}

export default memo(FileTree);

import { memo } from 'react';
import { formatSize } from '../utils/tree.js';

function FileTree({ nodes, currentFile, expandedDirs, onToggleDir, onSelectFile, depth }) {
  return (
    <ul className="tree-list" style={{ '--depth': depth }}>
      {nodes.map(node => {
        if (node.type === 'dir') {
          const isExpanded = expandedDirs[node.path] ?? false;
          return (
            <li key={node.path} className="tree-dir">
              <button
                className={`tree-dir-btn ${isExpanded ? 'expanded' : ''}`}
                onClick={() => onToggleDir(node.path)}
              >
                <span className="tree-icon">{isExpanded ? '📂' : '📁'}</span>
                <span className="tree-name">{node.name}</span>
                {node.children && (
                  <span className="tree-count">{countFiles(node)}</span>
                )}
              </button>
              {isExpanded && node.children && (
                <FileTree
                  nodes={node.children}
                  currentFile={currentFile}
                  expandedDirs={expandedDirs}
                  onToggleDir={onToggleDir}
                  onSelectFile={onSelectFile}
                  depth={depth + 1}
                />
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
              <span className="tree-icon">📄</span>
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

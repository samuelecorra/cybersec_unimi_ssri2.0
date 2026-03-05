import { memo, useMemo } from 'react';
import { flattenFiles } from '../utils/tree.js';

/* ── SVG Icons ── */
const BackArrow = () => (
  <svg width="14" height="14" viewBox="0 0 14 14" fill="none">
    <path d="M9 2L4 7L9 12" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

const YearIcon = ({ index }) => {
  const colors = ['var(--neon-cyan)', 'var(--neon-magenta)', 'var(--neon-lime)'];
  const color = colors[index % 3];
  return (
    <svg width="48" height="48" viewBox="0 0 48 48" fill="none">
      <polygon points="24,2 44,13 44,35 24,46 4,35 4,13" stroke={color} strokeWidth="1.5" fill={color} fillOpacity="0.08" />
      <polygon points="24,8 38,16 38,32 24,40 10,32 10,16" stroke={color} strokeWidth="0.8" fill={color} fillOpacity="0.05" opacity="0.6" />
      <text x="24" y="28" textAnchor="middle" fill={color} fontSize="14" fontFamily="var(--font-display)" fontWeight="700">
        {index + 1}
      </text>
    </svg>
  );
};

const SubjectIcon = () => (
  <svg width="28" height="28" viewBox="0 0 28 28" fill="none">
    <rect x="2" y="4" width="24" height="20" rx="2" stroke="currentColor" strokeWidth="1.3" fill="currentColor" fillOpacity="0.05" />
    <line x1="2" y1="10" x2="26" y2="10" stroke="currentColor" strokeWidth="0.8" opacity="0.3" />
    <line x1="7" y1="15" x2="21" y2="15" stroke="currentColor" strokeWidth="0.7" opacity="0.25" />
    <line x1="7" y1="19" x2="17" y2="19" stroke="currentColor" strokeWidth="0.7" opacity="0.25" />
  </svg>
);

const FolderIcon = () => (
  <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
    <path d="M2 6C2 4.9 2.9 4 4 4H9L11 6H20C21.1 6 22 6.9 22 8V18C22 19.1 21.1 20 20 20H4C2.9 20 2 19.1 2 18V6Z" stroke="currentColor" strokeWidth="1.2" fill="currentColor" fillOpacity="0.06" />
  </svg>
);

const LessonIcon = ({ active }) => (
  <svg width="18" height="20" viewBox="0 0 18 20" fill="none">
    <path d="M1 3C1 1.9 1.9 1 3 1H11L17 7V17C17 18.1 16.1 19 15 19H3C1.9 19 1 18.1 1 17V3Z" stroke="currentColor" strokeWidth="1" fill="currentColor" fillOpacity={active ? '0.12' : '0.04'} />
    <path d="M11 1V7H17" stroke="currentColor" strokeWidth="1" />
    <line x1="4" y1="11" x2="14" y2="11" stroke="currentColor" strokeWidth="0.6" opacity="0.3" />
    <line x1="4" y1="13.5" x2="12" y2="13.5" stroke="currentColor" strokeWidth="0.6" opacity="0.3" />
    <line x1="4" y1="16" x2="9" y2="16" stroke="currentColor" strokeWidth="0.6" opacity="0.3" />
  </svg>
);

const HomeIcon = () => (
  <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
    <polygon points="8,1 15,7 15,15 1,15 1,7" stroke="currentColor" strokeWidth="1.2" fill="currentColor" fillOpacity="0.06" />
    <rect x="6" y="10" width="4" height="5" stroke="currentColor" strokeWidth="0.8" fill="none" />
  </svg>
);

function countFilesInNode(node) {
  if (node.type === 'file') return 1;
  if (!node.children) return 0;
  return node.children.reduce((sum, c) => sum + countFilesInNode(c), 0);
}

function formatName(name) {
  return name
    .replace(/^\d+_/, '')     // remove leading number prefix like "1_"
    .replace(/_/g, ' ')       // underscores to spaces
    .replace(/\.md$/, '');    // remove .md extension
}

function BrowseView({ tree, browsePath, onBrowse, onSelectFile, currentFile }) {

  // Resolve current nodes from browsePath
  const { currentNodes, breadcrumbs, parentPath } = useMemo(() => {
    let nodes = tree;
    const crumbs = [];

    for (let i = 0; i < browsePath.length; i++) {
      const segment = browsePath[i];
      const found = nodes.find(n => n.name === segment && n.type === 'dir');
      if (!found || !found.children) break;
      crumbs.push({ name: found.name, path: browsePath.slice(0, i + 1) });
      nodes = found.children;
    }

    const parent = browsePath.length > 0 ? browsePath.slice(0, -1) : null;
    return { currentNodes: nodes, breadcrumbs: crumbs, parentPath: parent };
  }, [tree, browsePath]);

  const dirs = currentNodes.filter(n => n.type === 'dir');
  const files = currentNodes.filter(n => n.type === 'file');
  const isRoot = browsePath.length === 0;
  const isYearLevel = browsePath.length === 1;

  // Detect if we're at the "years" level (root usually has one dir: cybersecurity)
  // If root has exactly one dir, auto-drill into it for the UI
  const effectiveRoot = useMemo(() => {
    if (isRoot && dirs.length === 1 && files.length === 0) {
      return { autoSkip: true, skipName: dirs[0].name, nodes: dirs[0].children || [] };
    }
    return { autoSkip: false, nodes: currentNodes };
  }, [isRoot, dirs, files, currentNodes]);

  const displayNodes = effectiveRoot.autoSkip && isRoot ? effectiveRoot.nodes : currentNodes;
  const displayDirs = displayNodes.filter(n => n.type === 'dir');
  const displayFiles = displayNodes.filter(n => n.type === 'file');

  // Determine if these dirs are "years" (anno1, anno2, anno3)
  const isYearsView = isRoot && effectiveRoot.autoSkip &&
    displayDirs.every(d => /^anno\d+$/i.test(d.name));

  const handleDirClick = (dirName) => {
    if (isRoot && effectiveRoot.autoSkip) {
      // We skipped the root dir (cybersecurity), include it in path
      onBrowse([effectiveRoot.skipName, dirName]);
    } else {
      onBrowse([...browsePath, dirName]);
    }
  };

  const handleBack = () => {
    if (browsePath.length === 0) return;
    // If we auto-skipped root and we're at depth 2 (e.g. [cybersecurity, anno1]), go back to root
    if (effectiveRoot.autoSkip && browsePath.length <= 2) {
      onBrowse([]);
    } else {
      onBrowse(browsePath.slice(0, -1));
    }
  };

  const handleHome = () => {
    onBrowse([]);
  };

  // Actual breadcrumb trail
  const displayCrumbs = useMemo(() => {
    if (!effectiveRoot.autoSkip) return breadcrumbs;
    // Skip the first crumb (auto-skipped root)
    return breadcrumbs.slice(1);
  }, [breadcrumbs, effectiveRoot.autoSkip]);

  const canGoBack = browsePath.length > 0;

  // The neon color changes per depth
  const depthColors = ['var(--neon-cyan)', 'var(--neon-magenta)', 'var(--neon-lime)', 'var(--neon-yellow)'];
  const accentColor = depthColors[Math.min(displayCrumbs.length, depthColors.length - 1)];

  return (
    <div className="browse-view">
      {/* Navigation bar */}
      <div className="browse-nav">
        <div className="browse-nav-left">
          {canGoBack && (
            <button className="browse-back-btn" onClick={handleBack} title="Indietro">
              <BackArrow />
              <span>Indietro</span>
            </button>
          )}
          {displayCrumbs.length > 0 && canGoBack && (
            <button className="browse-home-btn" onClick={handleHome} title="Home">
              <HomeIcon />
            </button>
          )}
        </div>

        {displayCrumbs.length > 0 && (
          <div className="browse-breadcrumb">
            {displayCrumbs.map((crumb, i) => (
              <span key={i} className="browse-crumb">
                {i > 0 && <span className="browse-crumb-sep">›</span>}
                <button
                  className="browse-crumb-btn"
                  onClick={() => onBrowse(crumb.path)}
                >
                  {formatName(crumb.name)}
                </button>
              </span>
            ))}
          </div>
        )}
      </div>

      {/* Title */}
      {isRoot && (
        <div className="browse-hero">
          <div className="browse-hero-icon">
            <svg width="56" height="56" viewBox="0 0 56 56" fill="none">
              <polygon points="28,2 52,15.5 52,42.5 28,56 4,42.5 4,15.5" stroke="var(--neon-cyan)" strokeWidth="1.2" fill="var(--neon-cyan)" fillOpacity="0.04" />
              <polygon points="28,9 46,19.5 46,38.5 28,49 10,38.5 10,19.5" stroke="var(--neon-magenta)" strokeWidth="0.8" fill="var(--neon-magenta)" fillOpacity="0.03" opacity="0.6" />
              <circle cx="28" cy="28" r="3" fill="var(--neon-cyan)" opacity="0.7" />
            </svg>
          </div>
          <h2 className="browse-hero-title">Scegli il tuo anno</h2>
          <p className="browse-hero-subtitle">Seleziona un percorso per esplorare le lezioni</p>
        </div>
      )}

      {/* Grid cards */}
      <div className={`browse-grid ${isYearsView ? 'browse-grid--years' : ''} ${displayFiles.length > 0 && displayDirs.length === 0 ? 'browse-grid--files' : ''}`}>
        {displayDirs.map((dir, i) => {
          const fileCount = countFilesInNode(dir);
          const subDirs = (dir.children || []).filter(c => c.type === 'dir');
          const subFiles = (dir.children || []).filter(c => c.type === 'file');

          return (
            <button
              key={dir.path}
              className={`browse-card ${isYearsView ? 'browse-card--year' : 'browse-card--subject'}`}
              onClick={() => handleDirClick(dir.name)}
              style={{ '--card-accent': isYearsView ? depthColors[i % 3] : accentColor }}
            >
              <div className="browse-card-icon">
                {isYearsView ? (
                  <YearIcon index={i} />
                ) : subDirs.length > 0 ? (
                  <FolderIcon />
                ) : (
                  <SubjectIcon />
                )}
              </div>
              <div className="browse-card-info">
                <h3 className="browse-card-title">{formatName(dir.name)}</h3>
                <div className="browse-card-meta">
                  <span className="browse-card-count">{fileCount} lezioni</span>
                  {subDirs.length > 0 && (
                    <span className="browse-card-sub">{subDirs.length} sezioni</span>
                  )}
                </div>
              </div>
              <div className="browse-card-arrow">
                <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
                  <path d="M4 2L8.5 6L4 10" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                </svg>
              </div>
            </button>
          );
        })}

        {/* File items at this level */}
        {displayFiles.map((file) => {
          const isActive = currentFile === file.path;
          return (
            <button
              key={file.path}
              className={`browse-card browse-card--lesson ${isActive ? 'active' : ''}`}
              onClick={() => onSelectFile(file.path)}
              style={{ '--card-accent': accentColor }}
            >
              <div className="browse-card-icon">
                <LessonIcon active={isActive} />
              </div>
              <div className="browse-card-info">
                <h3 className="browse-card-title">{formatName(file.name)}</h3>
              </div>
              <div className="browse-card-arrow">
                <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
                  <path d="M4 2L8.5 6L4 10" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
                </svg>
              </div>
            </button>
          );
        })}
      </div>
    </div>
  );
}

export default memo(BrowseView);

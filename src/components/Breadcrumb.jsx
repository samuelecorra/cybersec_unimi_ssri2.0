import { memo } from 'react';

const BreadcrumbChevron = () => (
  <svg className="breadcrumb-chevron" width="10" height="10" viewBox="0 0 10 10" fill="none">
    <path d="M3 1.5L7 5L3 8.5" stroke="currentColor" strokeWidth="1.4" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

function formatSegment(name) {
  return name
    .replace(/^\d+_/, '')
    .replace(/_/g, ' ')
    .replace(/\.md$/, '');
}

function Breadcrumb({ path, onNavigate }) {
  if (!path) return null;

  const parts = path.split('/');
  const segments = parts.map((part, i) => ({
    name: part,
    path: parts.slice(0, i + 1).join('/'),
    isLast: i === parts.length - 1,
  }));

  // If too many segments, collapse middle ones
  const maxVisible = 4;
  let displaySegments = segments;
  if (segments.length > maxVisible) {
    displaySegments = [
      segments[0],
      { name: '…', path: '__ellipsis__', isEllipsis: true },
      ...segments.slice(-2),
    ];
  }

  return (
    <nav className="breadcrumb" title={parts.map(formatSegment).join(' / ')}>
      {displaySegments.map((seg, i) => (
        <span key={seg.path} className="breadcrumb-segment">
          {i > 0 && <span className="breadcrumb-sep"><BreadcrumbChevron /></span>}
          {seg.isEllipsis ? (
            <span className="breadcrumb-dir">…</span>
          ) : seg.isLast ? (
            <span className="breadcrumb-current" title={formatSegment(seg.name)}>{formatSegment(seg.name)}</span>
          ) : (
            <span className="breadcrumb-dir" title={formatSegment(seg.name)}>{formatSegment(seg.name)}</span>
          )}
        </span>
      ))}
    </nav>
  );
}

export default memo(Breadcrumb);

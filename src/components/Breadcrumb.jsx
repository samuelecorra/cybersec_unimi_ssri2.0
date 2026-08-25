import { memo } from 'react';

const BreadcrumbChevron = () => (
  <svg className="shrink-0" width="10" height="10" viewBox="0 0 10 10" fill="none">
    <path d="M3 1.5L7 5L3 8.5" stroke="currentColor" strokeWidth="1.4" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

function formatSegment(name) {
  return name
    .replace(/^\d+_/, '')
    .replace(/_/g, ' ')
    .replace(/\.(md|html)$/, '');
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
    <nav
      className="flex min-w-0 items-center gap-1 overflow-hidden whitespace-nowrap font-mono text-[0.8rem]"
      title={parts.map(formatSegment).join(' / ')}
    >
      {displaySegments.map((seg, i) => (
        <span key={seg.path} className="flex min-w-0 shrink items-center gap-1 last:overflow-hidden">
          {i > 0 && (
            <span className="mx-0.5 flex shrink-0 items-center text-text-muted">
              <BreadcrumbChevron />
            </span>
          )}
          {seg.isEllipsis ? (
            <span className="text-text-secondary">…</span>
          ) : seg.isLast ? (
            <span
              className="overflow-hidden text-ellipsis whitespace-nowrap font-semibold text-neon-cyan [text-shadow:0_0_8px_var(--glow-cyan)]"
              title={formatSegment(seg.name)}
            >
              {formatSegment(seg.name)}
            </span>
          ) : (
            <span
              className="max-w-[160px] overflow-hidden text-ellipsis whitespace-nowrap text-text-secondary"
              title={formatSegment(seg.name)}
            >
              {formatSegment(seg.name)}
            </span>
          )}
        </span>
      ))}
    </nav>
  );
}

export default memo(Breadcrumb);

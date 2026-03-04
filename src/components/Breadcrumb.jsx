import { memo } from 'react';

const BreadcrumbChevron = () => (
  <svg className="breadcrumb-chevron" width="8" height="8" viewBox="0 0 8 8" fill="none">
    <path d="M2.5 1L5.5 4L2.5 7" stroke="currentColor" strokeWidth="1.2" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

function Breadcrumb({ path, onNavigate }) {
  if (!path) return null;

  const parts = path.split('/');
  const segments = parts.map((part, i) => ({
    name: part,
    path: parts.slice(0, i + 1).join('/'),
    isLast: i === parts.length - 1,
  }));

  return (
    <nav className="breadcrumb">
      {segments.map((seg, i) => (
        <span key={seg.path} className="breadcrumb-segment">
          {i > 0 && <span className="breadcrumb-sep"><BreadcrumbChevron /></span>}
          {seg.isLast ? (
            <span className="breadcrumb-current">{seg.name.replace(/\.md$/, '')}</span>
          ) : (
            <span className="breadcrumb-dir">{seg.name}</span>
          )}
        </span>
      ))}
    </nav>
  );
}

export default memo(Breadcrumb);

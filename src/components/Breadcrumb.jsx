import { memo } from 'react';

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
          {i > 0 && <span className="breadcrumb-sep">/</span>}
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

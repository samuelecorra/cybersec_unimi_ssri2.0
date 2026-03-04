import { memo } from 'react';

function Navigation({ onPrev, onNext, hasPrev, hasNext, currentIndex, total }) {
  return (
    <div className="navigation">
      <button
        className="nav-btn"
        onClick={onPrev}
        disabled={!hasPrev}
        title="File precedente"
      >
        ← Prec
      </button>
      <span className="nav-counter">
        {currentIndex >= 0 ? currentIndex + 1 : '—'} / {total}
      </span>
      <button
        className="nav-btn"
        onClick={onNext}
        disabled={!hasNext}
        title="File successivo"
      >
        Succ →
      </button>
    </div>
  );
}

export default memo(Navigation);

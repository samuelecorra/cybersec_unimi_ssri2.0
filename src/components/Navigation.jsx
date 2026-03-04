import { memo } from 'react';

const ArrowLeft = () => (
  <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
    <path d="M7.5 2L3.5 6L7.5 10" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

const ArrowRight = () => (
  <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
    <path d="M4.5 2L8.5 6L4.5 10" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

function Navigation({ onPrev, onNext, hasPrev, hasNext, currentIndex, total }) {
  return (
    <div className="navigation">
      <button
        className="nav-btn"
        onClick={onPrev}
        disabled={!hasPrev}
        title="File precedente"
      >
        <ArrowLeft /> Prec
      </button>
      <span className="nav-counter">
        <span className="nav-counter-current">{currentIndex >= 0 ? currentIndex + 1 : '—'}</span>
        <span className="nav-counter-sep">/</span>
        <span className="nav-counter-total">{total}</span>
      </span>
      <button
        className="nav-btn"
        onClick={onNext}
        disabled={!hasNext}
        title="File successivo"
      >
        Succ <ArrowRight />
      </button>
    </div>
  );
}

export default memo(Navigation);

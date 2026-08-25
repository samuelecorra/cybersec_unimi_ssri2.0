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

const SinglePageIcon = () => (
  <svg width="13" height="13" viewBox="0 0 14 14" fill="none">
    <rect x="3" y="1.5" width="8" height="11" rx="1" stroke="currentColor" strokeWidth="1.3" />
  </svg>
);

const DualPageIcon = () => (
  <svg width="13" height="13" viewBox="0 0 14 14" fill="none">
    <rect x="0.5" y="1.5" width="5.7" height="11" rx="1" stroke="currentColor" strokeWidth="1.2" />
    <rect x="7.8" y="1.5" width="5.7" height="11" rx="1" stroke="currentColor" strokeWidth="1.2" />
  </svg>
);

function Navigation({ onPrev, onNext, hasPrev, hasNext, currentIndex, total, readingMode, onToggleReadingMode }) {
  const isDual = readingMode === 'dual';
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
      {onToggleReadingMode && (
        <button
          className="nav-btn reading-mode-toggle"
          onClick={onToggleReadingMode}
          title={isDual ? 'Vista a doppia pagina (clic per pagina singola)' : 'Vista a pagina singola (clic per doppia pagina)'}
          aria-pressed={isDual}
        >
          {isDual ? <DualPageIcon /> : <SinglePageIcon />}
        </button>
      )}
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

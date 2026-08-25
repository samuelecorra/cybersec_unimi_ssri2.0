import { memo } from 'react';

const ArrowLeft = () => (
  <svg className="shrink-0" width="12" height="12" viewBox="0 0 12 12" fill="none">
    <path d="M7.5 2L3.5 6L7.5 10" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

const ArrowRight = () => (
  <svg className="shrink-0" width="12" height="12" viewBox="0 0 12 12" fill="none">
    <path d="M4.5 2L8.5 6L4.5 10" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
  </svg>
);

const SinglePageIcon = () => (
  <svg className="shrink-0" width="13" height="13" viewBox="0 0 14 14" fill="none">
    <rect x="3" y="1.5" width="8" height="11" rx="1" stroke="currentColor" strokeWidth="1.3" />
  </svg>
);

const DualPageIcon = () => (
  <svg className="shrink-0" width="13" height="13" viewBox="0 0 14 14" fill="none">
    <rect x="0.5" y="1.5" width="5.7" height="11" rx="1" stroke="currentColor" strokeWidth="1.2" />
    <rect x="7.8" y="1.5" width="5.7" height="11" rx="1" stroke="currentColor" strokeWidth="1.2" />
  </svg>
);

const navBtn =
  'glow-btn relative flex items-center gap-1 whitespace-nowrap rounded-md border border-border bg-glass-bg px-3 py-1 font-mono text-[0.8rem] text-neon-cyan';

function Navigation({ onPrev, onNext, hasPrev, hasNext, currentIndex, total, readingMode, onToggleReadingMode }) {
  const isDual = readingMode === 'dual';
  return (
    <div className="flex shrink-0 items-center gap-2 max-[768px]:w-full max-[768px]:justify-between">
      <button className={navBtn} onClick={onPrev} disabled={!hasPrev} title="File precedente">
        <ArrowLeft /> Prec
      </button>
      {onToggleReadingMode && (
        <button
          className={`${navBtn} max-[1100px]:hidden !px-2 aria-pressed:border-neon-magenta aria-pressed:text-neon-magenta aria-pressed:shadow-[0_0_12px_var(--glow-magenta)] aria-pressed:[--glow-color:var(--glow-magenta)]`}
          onClick={onToggleReadingMode}
          title={isDual ? 'Vista a doppia pagina (clic per pagina singola)' : 'Vista a pagina singola (clic per doppia pagina)'}
          aria-pressed={isDual}
        >
          {isDual ? <DualPageIcon /> : <SinglePageIcon />}
        </button>
      )}
      <span className="flex items-center gap-[3px] whitespace-nowrap font-mono text-[0.75rem]">
        <span className="font-semibold text-neon-cyan">{currentIndex >= 0 ? currentIndex + 1 : '—'}</span>
        <span className="text-text-muted">/</span>
        <span className="text-text-muted">{total}</span>
      </span>
      <button className={navBtn} onClick={onNext} disabled={!hasNext} title="File successivo">
        Succ <ArrowRight />
      </button>
    </div>
  );
}

export default memo(Navigation);

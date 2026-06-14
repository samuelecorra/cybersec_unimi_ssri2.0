import { memo, useMemo, useState, useEffect, useRef, useCallback } from "react";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import remarkMath from "remark-math";
import remarkCallouts from "../utils/remarkCallouts.js";
import rehypeHighlight from "rehype-highlight";
import rehypeKatex from "rehype-katex";
import { getParentPath } from "../utils/tree.js";

function Viewer({ content, currentFile, loading }) {
  const dirPath = useMemo(() => (currentFile ? getParentPath(currentFile) : ""), [currentFile]);
  const [lightboxSrc, setLightboxSrc] = useState(null);
  const [lightboxAlt, setLightboxAlt] = useState("");
  const [zoom, setZoom] = useState(1);
  const articleRef = useRef(null);
  const scrollSaveRef = useRef(0);
  const scrollAreaRef = useRef(null);
  const isDragging = useRef(false);
  const dragStart = useRef({ x: 0, y: 0, scrollLeft: 0, scrollTop: 0 });

  const openLightbox = useCallback((src, alt) => {
    const container = articleRef.current?.parentElement;
    scrollSaveRef.current = container?.scrollTop ?? 0;
    setLightboxSrc(src);
    setLightboxAlt(alt || "");
    setZoom(1);
  }, []);

  const closeLightbox = useCallback(() => {
    setLightboxSrc(null);
    setZoom(1);
    requestAnimationFrame(() => {
      const container = articleRef.current?.parentElement;
      if (container) container.scrollTop = scrollSaveRef.current;
    });
  }, []);

  // ESC to close
  useEffect(() => {
    if (!lightboxSrc) return;
    const handler = (e) => { if (e.key === "Escape") closeLightbox(); };
    window.addEventListener("keydown", handler);
    return () => window.removeEventListener("keydown", handler);
  }, [lightboxSrc, closeLightbox]);

  // Mouse drag-to-pan: global move/up listeners attached while lightbox is open
  const onMouseDown = useCallback((e) => {
    if (e.button !== 0 || !scrollAreaRef.current) return;
    isDragging.current = true;
    dragStart.current = {
      x: e.clientX,
      y: e.clientY,
      scrollLeft: scrollAreaRef.current.scrollLeft,
      scrollTop: scrollAreaRef.current.scrollTop,
    };
    scrollAreaRef.current.classList.add("is-dragging");
    e.preventDefault();
  }, []);

  useEffect(() => {
    if (!lightboxSrc) return;
    const onMouseMove = (e) => {
      if (!isDragging.current || !scrollAreaRef.current) return;
      scrollAreaRef.current.scrollLeft = dragStart.current.scrollLeft - (e.clientX - dragStart.current.x);
      scrollAreaRef.current.scrollTop  = dragStart.current.scrollTop  - (e.clientY - dragStart.current.y);
    };
    const onMouseUp = () => {
      if (!isDragging.current) return;
      isDragging.current = false;
      scrollAreaRef.current?.classList.remove("is-dragging");
    };
    window.addEventListener("mousemove", onMouseMove);
    window.addEventListener("mouseup", onMouseUp);
    return () => {
      window.removeEventListener("mousemove", onMouseMove);
      window.removeEventListener("mouseup", onMouseUp);
    };
  }, [lightboxSrc]);

  // Ctrl+wheel → zoom image, intercept before browser handles it
  useEffect(() => {
    if (!lightboxSrc) return;
    const handleWheel = (e) => {
      if (!e.ctrlKey) return;
      e.preventDefault();
      const delta = e.deltaY < 0 ? 0.15 : -0.15;
      setZoom((prev) => Math.min(Math.max(0.3, prev + delta), 8));
    };
    window.addEventListener("wheel", handleWheel, { passive: false });
    return () => window.removeEventListener("wheel", handleWheel);
  }, [lightboxSrc]);

  const components = useMemo(
    () => ({
      img: ({ src, alt, ...props }) => {
        if (src && !src.startsWith("http") && !src.startsWith("data:")) {
          // Decode %20 etc. first, then resolve relative path (../)
          const decoded = decodeURIComponent(src);
          const parts = dirPath ? `${dirPath}/${decoded}`.split("/") : decoded.split("/");
          const resolved = [];
          for (const p of parts) {
            if (p === "..") resolved.pop();
            else if (p && p !== ".") resolved.push(p);
          }
          const resolvedPath = resolved.join("/");
          const base = import.meta.env.BASE_URL;
          const resolvedSrc = `${base}lessons/${encodeURI(resolvedPath)}`;
          return (
            <img
              src={resolvedSrc}
              alt={alt || ""}
              className="md-image"
              loading="lazy"
              onClick={() => openLightbox(resolvedSrc, alt)}
              {...props}
            />
          );
        }
        return (
          <img
            src={src}
            alt={alt || ""}
            className="md-image"
            loading="lazy"
            onClick={() => openLightbox(src, alt)}
            {...props}
          />
        );
      },
      table: ({ children, ...props }) => (
        <div className="table-wrapper">
          <table {...props}>{children}</table>
        </div>
      ),
      a: ({ href, children, ...props }) => (
        <a href={href} target="_blank" rel="noopener noreferrer" {...props}>
          {children}
        </a>
      ),
      code: ({ children, className, node, ...props }) => {
        const isInline = !className;
        if (isInline) {
          return (
            <code className="inline-code" {...props}>
              {children}
            </code>
          );
        }
        return (
          <code className={className} {...props}>
            {children}
          </code>
        );
      },
    }),
    [dirPath, openLightbox],
  );

  if (!currentFile) {
    return (
      <div className="viewer-empty">
        <div className="viewer-empty-icon">
          <svg width="72" height="72" viewBox="0 0 72 72" fill="none">
            <polygon
              points="36,2 66,19 66,53 36,70 6,53 6,19"
              stroke="currentColor"
              strokeWidth="1.5"
              fill="currentColor"
              fillOpacity="0.04"
            />
            <polygon
              points="36,10 58,23 58,49 36,62 14,49 14,23"
              stroke="currentColor"
              strokeWidth="1"
              fill="currentColor"
              fillOpacity="0.06"
              opacity="0.6"
            />
            <polygon
              points="36,18 50,27 50,45 36,54 22,45 22,27"
              stroke="currentColor"
              strokeWidth="0.8"
              fill="currentColor"
              fillOpacity="0.08"
              opacity="0.3"
            />
            <circle cx="36" cy="36" r="3" fill="currentColor" opacity="0.8" />
          </svg>
        </div>
        <h2>Samu CyberLocker</h2>
        <p className="viewer-empty-subtitle">Knowledge Vault System</p>
        <div className="viewer-empty-divider"></div>
        <p className="viewer-empty-hint">Seleziona un file dalla sidebar per iniziare</p>
        <p className="viewer-empty-hint">
          <kbd>Ctrl</kbd> + <kbd>K</kbd> per cercare
        </p>
      </div>
    );
  }

  if (loading) {
    return (
      <div className="viewer-loading">
        <div className="loading-spinner" />
        <p>Caricamento...</p>
      </div>
    );
  }

  const imgZoomed = zoom !== 1;

  return (
    <>
      <article ref={articleRef} className="viewer-content markdown-body">
        <ReactMarkdown
          remarkPlugins={[remarkGfm, remarkMath, remarkCallouts]}
          rehypePlugins={[rehypeKatex, rehypeHighlight]}
          components={components}>
          {content}
        </ReactMarkdown>
      </article>

      {lightboxSrc && (
        <div
          className="lightbox-overlay"
          role="dialog"
          aria-modal="true"
          aria-label="Immagine ingrandita"
        >
          {/* X button: position:fixed so always visible regardless of scroll/zoom */}
          <button className="lightbox-close" onClick={closeLightbox} aria-label="Chiudi">✕</button>

          {/* Scrollable + draggable container for panning in all 4 directions */}
          <div
            className="lightbox-scroll-area"
            ref={scrollAreaRef}
            onMouseDown={onMouseDown}
          >
            <img
              src={lightboxSrc}
              alt={lightboxAlt}
              className="lightbox-img"
              draggable="false"
              style={imgZoomed ? {
                width: `${zoom * 90}vw`,
                maxWidth: "none",
                maxHeight: "none",
                height: "auto",
              } : {}}
            />
          </div>

          <span className="lightbox-hint">
            {imgZoomed
              ? `${Math.round(zoom * 100)}% · Ctrl+rotella per zoom · ✕ o ESC per chiudere`
              : "Ctrl+rotella per zoom · ✕ o ESC per chiudere"}
          </span>
        </div>
      )}
    </>
  );
}

export default memo(Viewer);

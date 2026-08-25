import { useState, useEffect, useCallback, useMemo, useRef } from 'react';
import tree from 'virtual:lesson-tree';
import searchIndex from 'virtual:lesson-search';
import Sidebar from './components/Sidebar.jsx';
import Viewer from './components/Viewer.jsx';
import SourceViewer from './components/SourceViewer.jsx';
import AssetViewer from './components/AssetViewer.jsx';
import WebLessonViewer from './components/WebLessonViewer.jsx';
import PdfViewer from './components/PdfViewer.jsx';
import BrowseView from './components/BrowseView.jsx';
import Breadcrumb from './components/Breadcrumb.jsx';
import Navigation from './components/Navigation.jsx';
import SearchBar from './components/SearchBar.jsx';
import { HashRouter, useHashRouter } from './components/HashRouter.jsx';
import { flattenFiles } from './utils/tree.js';
import { encodePathSegments, getFileKind, isTextViewerFile } from './utils/fileTypes.js';
import { useLocalStorage } from './hooks/useLocalStorage.js';

function RoutedApp() {
  const {
    route,
    navigateHome,
    navigateToDirectory,
    navigateToFile,
    getParentDirectory,
  } = useHashRouter();
  const [currentFile, setCurrentFile] = useLocalStorage('cyberlocker:currentFile', null);
  const [expandedDirs, setExpandedDirs] = useLocalStorage('cyberlocker:expandedDirs', {});
  const [content, setContent] = useState('');
  const [loading, setLoading] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [browsePath, setBrowsePath] = useState([]);
  const [viewMode, setViewMode] = useLocalStorage('cyberlocker:viewMode', 'browse'); // 'browse' | 'viewer'
  const [readingMode, setReadingMode] = useLocalStorage('cyberlocker:readingMode', 'single'); // 'single' | 'dual'
  const loadRequestId = useRef(0);
  const legacyState = useRef({ currentFile, viewMode });

  const allFiles = useMemo(() => flattenFiles(tree), []);

  const currentIndex = useMemo(
    () => allFiles.findIndex(f => f === currentFile),
    [allFiles, currentFile]
  );

  const filteredResults = useMemo(() => {
    if (!searchQuery.trim()) return null;
    const q = searchQuery.toLowerCase();
    return searchIndex.filter(item => {
      if (item.path.toLowerCase().includes(q)) return true;
      if (item.headings.some(h => h.toLowerCase().includes(q))) return true;
      if (item.preview.toLowerCase().includes(q)) return true;
      return false;
    });
  }, [searchQuery]);

  const loadFile = useCallback(async (filePath) => {
    const requestId = ++loadRequestId.current;
    setLoading(true);
    try {
      const base = import.meta.env.BASE_URL;
      const url = `${base}lessons/${encodePathSegments(filePath)}`;
      const res = await fetch(url);
      if (!res.ok) throw new Error('Failed to load');
      const text = await res.text();
      if (requestId === loadRequestId.current) setContent(text);
    } catch {
      if (requestId === loadRequestId.current) {
        navigateToDirectory(getParentDirectory(filePath), { replace: true });
      }
    } finally {
      if (requestId === loadRequestId.current) setLoading(false);
    }
  }, [getParentDirectory, navigateToDirectory]);

  useEffect(() => {
    if (route.kind === 'unset') {
      const previous = legacyState.current;
      if (previous.viewMode === 'viewer' && previous.currentFile && allFiles.includes(previous.currentFile)) {
        navigateToFile(previous.currentFile, { replace: true });
      } else {
        navigateHome({ replace: true });
      }
      return;
    }

    if (route.kind === 'file') {
      const filePath = route.path;
      setCurrentFile(filePath);
      setBrowsePath(getParentDirectory(filePath));
      setContent('');
      setViewMode('viewer');
      if (isTextViewerFile(filePath)) {
        loadFile(filePath);
      } else {
        loadRequestId.current++;
        setLoading(false);
      }
      const pathSegments = filePath.split('/').slice(0, -1);
      setExpandedDirs(previous => {
        const next = { ...previous };
        for (let i = 1; i <= pathSegments.length; i++) {
          next[pathSegments.slice(0, i).join('/')] = true;
        }
        return next;
      });
      return;
    }

    loadRequestId.current++;
    setLoading(false);
    setCurrentFile(null);
    setContent('');
    setViewMode('browse');
    const directorySegments = route.kind === 'directory' ? route.segments : [];
    setBrowsePath(directorySegments);
    setExpandedDirs(previous => {
      const next = { ...previous };
      for (let i = 1; i <= directorySegments.length; i++) {
        next[directorySegments.slice(0, i).join('/')] = true;
      }
      return next;
    });
  }, [
    allFiles,
    getParentDirectory,
    loadFile,
    navigateHome,
    navigateToFile,
    route,
    setCurrentFile,
    setExpandedDirs,
    setViewMode,
  ]);

  const handleFileSelect = useCallback((filePath) => {
    if (navigateToFile(filePath)) setSearchQuery('');
  }, [navigateToFile]);

  const handleBrowse = useCallback((path) => {
    if (path.length === 0) navigateHome();
    else navigateToDirectory(path);
  }, [navigateHome, navigateToDirectory]);

  const handleBackToBrowse = useCallback(() => {
    navigateToDirectory(getParentDirectory(currentFile));
  }, [currentFile, getParentDirectory, navigateToDirectory]);

  const handlePrev = useCallback(() => {
    if (currentIndex > 0) {
      handleFileSelect(allFiles[currentIndex - 1]);
    }
  }, [currentIndex, allFiles, handleFileSelect]);

  const handleNext = useCallback(() => {
    if (currentIndex < allFiles.length - 1) {
      handleFileSelect(allFiles[currentIndex + 1]);
    }
  }, [currentIndex, allFiles, handleFileSelect]);

  const toggleReadingMode = useCallback(() => {
    setReadingMode(m => (m === 'dual' ? 'single' : 'dual'));
  }, [setReadingMode]);

  const isDualPage = readingMode === 'dual' && viewMode === 'viewer' && getFileKind(currentFile) === 'markdown';

  const toggleDir = useCallback((dirPath) => {
    setExpandedDirs(prev => ({
      ...prev,
      [dirPath]: !prev[dirPath],
    }));
  }, [setExpandedDirs]);

  return (
    <div className="app">
      <header className="header relative z-[100] flex h-[52px] shrink-0 items-center gap-4 border-b border-glass-border bg-glass-bg px-5 backdrop-blur-[20px] max-[768px]:gap-2.5 max-[768px]:px-3">
        <div className="flex shrink-0 items-center gap-3">
          <button
            className="glow-btn flex h-8 w-8 items-center justify-center rounded-md border border-border bg-transparent text-neon-cyan"
            onClick={() => setSidebarOpen(o => !o)}
            title={sidebarOpen ? 'Chiudi sidebar' : 'Apri sidebar'}
          >
            <svg className="shrink-0" width="14" height="14" viewBox="0 0 14 14" fill="none">
              {sidebarOpen ? (
                <>
                  <rect x="1" y="1" width="12" height="12" rx="2" stroke="currentColor" strokeWidth="1.3" />
                  <line x1="5" y1="1" x2="5" y2="13" stroke="currentColor" strokeWidth="1.3" />
                </>
              ) : (
                <>
                  <rect x="1" y="1" width="12" height="12" rx="2" stroke="currentColor" strokeWidth="1.3" />
                  <line x1="5" y1="1" x2="5" y2="13" stroke="currentColor" strokeWidth="1.3" opacity="0.4" />
                  <path d="M7.5 5.5L9.5 7L7.5 8.5" stroke="currentColor" strokeWidth="1.3" strokeLinecap="round" strokeLinejoin="round" />
                </>
              )}
            </svg>
          </button>
          <h1 className="flex items-center gap-2.5 whitespace-nowrap">
            <svg className="logo-hex" width="22" height="24" viewBox="0 0 22 24" fill="none">
              <polygon points="11,1 21,6.5 21,17.5 11,23 1,17.5 1,6.5" stroke="currentColor" strokeWidth="1.5" fill="currentColor" fillOpacity="0.08" />
              <polygon points="11,5 17,8.5 17,15.5 11,19 5,15.5 5,8.5" stroke="currentColor" strokeWidth="0.8" fill="currentColor" fillOpacity="0.06" opacity="0.6" />
              <circle cx="11" cy="12" r="2" fill="currentColor" />
            </svg>
            <span className="logo-text hidden sm:inline">SSRI 2.0</span>
          </h1>
        </div>
        <SearchBar query={searchQuery} onChange={setSearchQuery} />
        <div className="hidden shrink-0 items-center gap-3 sm:flex">
          <span className="rounded-xl border border-border bg-bg-mid px-2.5 py-1 font-mono text-[0.8rem] text-text-muted">
            {allFiles.length} files
          </span>
        </div>
      </header>

      <div className="main">
        {sidebarOpen && (
          <div
            className="sidebar-backdrop"
            onClick={() => setSidebarOpen(false)}
            aria-hidden="true"
          />
        )}
        {sidebarOpen && (
          <Sidebar
            tree={tree}
            currentFile={currentFile}
            expandedDirs={expandedDirs}
            onToggleDir={toggleDir}
            onSelectFile={handleFileSelect}
            searchResults={filteredResults}
            searchQuery={searchQuery}
          />
        )}
        <div className={`content-area${isDualPage ? ' dual-page-active' : ''}`}>
          {viewMode === 'viewer' && currentFile ? (
            <>
              <div className="content-header">
                <div className="content-header-left">
                  <button
                    className="glow-btn flex h-7 w-7 shrink-0 items-center justify-center rounded-md border border-border bg-glass-bg text-neon-cyan"
                    onClick={handleBackToBrowse}
                    title="Torna alla navigazione"
                  >
                    <svg width="14" height="14" viewBox="0 0 14 14" fill="none">
                      <path d="M9 2L4 7L9 12" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
                    </svg>
                  </button>
                  <Breadcrumb path={currentFile} onNavigate={handleFileSelect} />
                </div>
                <Navigation
                  onPrev={handlePrev}
                  onNext={handleNext}
                  hasPrev={currentIndex > 0}
                  hasNext={currentIndex < allFiles.length - 1}
                  currentIndex={currentIndex}
                  total={allFiles.length}
                  readingMode={getFileKind(currentFile) === 'markdown' ? readingMode : undefined}
                  onToggleReadingMode={getFileKind(currentFile) === 'markdown' ? toggleReadingMode : undefined}
                />
              </div>
              {getFileKind(currentFile) === 'web-lesson' ? (
                <WebLessonViewer filePath={currentFile} />
              ) : getFileKind(currentFile) === 'pdf' ? (
                <PdfViewer filePath={currentFile} />
              ) : getFileKind(currentFile) === 'source' ? (
                <SourceViewer
                  content={content}
                  currentFile={currentFile}
                  loading={loading}
                />
              ) : ['image', 'audio'].includes(getFileKind(currentFile)) ? (
                <AssetViewer currentFile={currentFile} />
              ) : (
                <Viewer
                  content={content}
                  currentFile={currentFile}
                  loading={loading}
                  onFileSelect={handleFileSelect}
                  readingMode={readingMode}
                />
              )}
            </>
          ) : (
            <BrowseView
              tree={tree}
              browsePath={browsePath}
              onBrowse={handleBrowse}
              onSelectFile={handleFileSelect}
              currentFile={currentFile}
            />
          )}
        </div>
      </div>
      <footer className="app-footer">
        <span className="footer-notice">
          SSRI 2.0 — Archivio accademico non a fini di lucro · Lezioni UniMI trasposte in Markdown, integrate con temi d&apos;esame risolti e commentati per una fruizione completa del materiale universitario.
        </span>
        <span className="footer-author">
          Samuele Corrà
          <span className="footer-sep">·</span>
          <a href="mailto:samuele.corra@studenti.unimi.it" className="footer-mail">samuele.corra@studenti.unimi.it</a>
          <span className="footer-sep">·</span>
          <span className="footer-collab">Collaboratori benvenuti</span>
        </span>
      </footer>
    </div>
  );
}

export default function App() {
  return (
    <HashRouter tree={tree}>
      <RoutedApp />
    </HashRouter>
  );
}

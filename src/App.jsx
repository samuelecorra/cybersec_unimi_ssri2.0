import { useState, useEffect, useCallback, useMemo } from 'react';
import tree from 'virtual:lesson-tree';
import searchIndex from 'virtual:lesson-search';
import Sidebar from './components/Sidebar.jsx';
import Viewer from './components/Viewer.jsx';
import Breadcrumb from './components/Breadcrumb.jsx';
import Navigation from './components/Navigation.jsx';
import SearchBar from './components/SearchBar.jsx';
import { flattenFiles } from './utils/tree.js';
import { useLocalStorage } from './hooks/useLocalStorage.js';

export default function App() {
  const [currentFile, setCurrentFile] = useLocalStorage('cyberlocker:currentFile', null);
  const [expandedDirs, setExpandedDirs] = useLocalStorage('cyberlocker:expandedDirs', {});
  const [content, setContent] = useState('');
  const [loading, setLoading] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [sidebarOpen, setSidebarOpen] = useState(true);

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
    setLoading(true);
    try {
      const res = await fetch(`/api/file?path=${encodeURIComponent(filePath)}`);
      if (!res.ok) throw new Error('Failed to load');
      const text = await res.text();
      setContent(text);
      setCurrentFile(filePath);
    } catch (err) {
      setContent(`# Errore\n\nImpossibile caricare il file: ${filePath}\n\n${err.message}`);
    } finally {
      setLoading(false);
    }
  }, [setCurrentFile]);

  useEffect(() => {
    if (currentFile) {
      loadFile(currentFile);
    }
  }, []);

  const handleFileSelect = useCallback((filePath) => {
    loadFile(filePath);
    setSearchQuery('');
  }, [loadFile]);

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

  const toggleDir = useCallback((dirPath) => {
    setExpandedDirs(prev => ({
      ...prev,
      [dirPath]: !prev[dirPath],
    }));
  }, [setExpandedDirs]);

  return (
    <div className="app">
      <header className="header">
        <div className="header-left">
          <button
            className="sidebar-toggle"
            onClick={() => setSidebarOpen(o => !o)}
            title={sidebarOpen ? 'Chiudi sidebar' : 'Apri sidebar'}
          >
            <svg width="14" height="14" viewBox="0 0 14 14" fill="none">
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
          <h1 className="logo">
            <svg className="logo-hex" width="22" height="24" viewBox="0 0 22 24" fill="none">
              <polygon points="11,1 21,6.5 21,17.5 11,23 1,17.5 1,6.5" stroke="currentColor" strokeWidth="1.5" fill="currentColor" fillOpacity="0.08" />
              <polygon points="11,5 17,8.5 17,15.5 11,19 5,15.5 5,8.5" stroke="currentColor" strokeWidth="0.8" fill="currentColor" fillOpacity="0.06" opacity="0.6" />
              <circle cx="11" cy="12" r="2" fill="currentColor" />
            </svg>
            <span className="logo-text">Samu CyberLocker</span>
          </h1>
        </div>
        <SearchBar query={searchQuery} onChange={setSearchQuery} />
        <div className="header-right">
          <span className="file-count">{allFiles.length} files</span>
        </div>
      </header>

      <div className="main">
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
        <div className="content-area">
          {currentFile && (
            <div className="content-header">
              <Breadcrumb path={currentFile} onNavigate={handleFileSelect} />
              <Navigation
                onPrev={handlePrev}
                onNext={handleNext}
                hasPrev={currentIndex > 0}
                hasNext={currentIndex < allFiles.length - 1}
                currentIndex={currentIndex}
                total={allFiles.length}
              />
            </div>
          )}
          <Viewer
            content={content}
            currentFile={currentFile}
            loading={loading}
          />
        </div>
      </div>
    </div>
  );
}

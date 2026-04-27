import { useState, useEffect, memo } from 'react';
import hljs from 'highlight.js/lib/core';
import xml from 'highlight.js/lib/languages/xml';
import javascript from 'highlight.js/lib/languages/javascript';
import cssLang from 'highlight.js/lib/languages/css';
import { getParentPath } from '../utils/tree.js';

hljs.registerLanguage('xml', xml);
hljs.registerLanguage('javascript', javascript);
hljs.registerLanguage('css', cssLang);

const TABS = [
  { key: 'html', label: 'HTML', lang: 'xml',        file: 'index.html', color: 'var(--neon-orange)' },
  { key: 'js',   label: 'JS',   lang: 'javascript', file: 'index.js',   color: 'var(--neon-yellow)' },
  { key: 'css',  label: 'CSS',  lang: 'css',        file: 'style.css',  color: 'var(--neon-cyan)'   },
];

function highlight(code, lang) {
  if (!code) return '';
  try {
    return hljs.highlight(code, { language: lang }).value;
  } catch {
    return code.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
  }
}

function WebLessonViewer({ filePath }) {
  const [files, setFiles] = useState({ html: null, js: null, css: null });
  const [loading, setLoading] = useState(false);
  const [activeTab, setActiveTab] = useState('html');

  useEffect(() => {
    if (!filePath) return;
    setLoading(true);
    setActiveTab('html');
    const dir = getParentPath(filePath);
    const base = import.meta.env.BASE_URL;

    const fetchFile = async (name) => {
      try {
        const res = await fetch(`${base}lessons/${dir}/${name}`);
        return res.ok ? await res.text() : '';
      } catch {
        return '';
      }
    };

    Promise.all([
      fetchFile('index.html'),
      fetchFile('index.js'),
      fetchFile('style.css'),
    ]).then(([html, js, css]) => {
      setFiles({ html, js, css });
    }).finally(() => setLoading(false));
  }, [filePath]);

  if (loading) {
    return (
      <div className="viewer-loading">
        <div className="loading-spinner" />
        <p>Caricamento...</p>
      </div>
    );
  }

  const activeTabDef = TABS.find(t => t.key === activeTab);
  const code = files[activeTab] ?? '';

  return (
    <div className="web-lesson-viewer">
      <div className="web-lesson-tabs">
        {TABS.map(tab => (
          <button
            key={tab.key}
            className={`web-lesson-tab${activeTab === tab.key ? ' active' : ''}`}
            style={{ '--tab-color': tab.color }}
            onClick={() => setActiveTab(tab.key)}
          >
            <span className="web-lesson-tab-dot" />
            {tab.label}
          </button>
        ))}
      </div>
      <pre className="web-lesson-code hljs">
        <code
          className={`language-${activeTabDef.lang}`}
          dangerouslySetInnerHTML={{ __html: highlight(code, activeTabDef.lang) }}
        />
      </pre>
    </div>
  );
}

export default memo(WebLessonViewer);

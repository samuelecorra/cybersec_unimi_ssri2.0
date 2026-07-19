import { createContext, useCallback, useContext, useEffect, useMemo, useState } from 'react';
import {
  buildHashRouteIndex,
  findNearestDirectory,
  resolveHashRoute,
  resolveKnownRoute,
} from '../utils/hashRouting.js';

const HashRouterContext = createContext(null);

function replaceLocationHash(hash) {
  const url = `${window.location.pathname}${window.location.search}${hash}`;
  window.history.replaceState(window.history.state, '', url);
}

export function HashRouter({ tree, children }) {
  const index = useMemo(() => buildHashRouteIndex(tree), [tree]);
  const [route, setRoute] = useState(() => {
    const initialRoute = resolveHashRoute(window.location.hash, index);
    return initialRoute.kind === 'invalid'
      ? resolveKnownRoute('home', '', index)
      : initialRoute;
  });

  useEffect(() => {
    const syncFromLocation = () => {
      const nextRoute = resolveHashRoute(window.location.hash, index);
      if (nextRoute.kind === 'invalid') {
        const homeRoute = resolveKnownRoute('home', '', index);
        replaceLocationHash(homeRoute.canonicalHash);
        setRoute(homeRoute);
        return;
      }
      if (nextRoute.kind !== 'unset' && window.location.hash !== nextRoute.canonicalHash) {
        replaceLocationHash(nextRoute.canonicalHash);
      }
      setRoute(nextRoute);
    };

    syncFromLocation();
    window.addEventListener('hashchange', syncFromLocation);
    return () => window.removeEventListener('hashchange', syncFromLocation);
  }, [index]);

  const commitRoute = useCallback((nextRoute, options = {}) => {
    if (!nextRoute) return false;
    const { replace = false } = options;
    if (replace) {
      replaceLocationHash(nextRoute.canonicalHash);
    } else if (window.location.hash !== nextRoute.canonicalHash) {
      window.location.hash = nextRoute.canonicalHash;
    }
    setRoute(nextRoute);
    return true;
  }, []);

  const navigateHome = useCallback(
    options => commitRoute(resolveKnownRoute('home', '', index), options),
    [commitRoute, index],
  );

  const navigateToDirectory = useCallback((path, options) => {
    const segments = Array.isArray(path) ? path : String(path ?? '').split('/');
    if (segments.length === 0 || (segments.length === 1 && segments[0] === '')) {
      return navigateHome(options);
    }
    return commitRoute(resolveKnownRoute('directory', segments, index), options);
  }, [commitRoute, index, navigateHome]);

  const navigateToFile = useCallback(
    (path, options) => commitRoute(resolveKnownRoute('file', path, index), options),
    [commitRoute, index],
  );

  const getParentDirectory = useCallback(
    filePath => findNearestDirectory(filePath, index),
    [index],
  );

  const value = useMemo(() => ({
    route,
    navigateHome,
    navigateToDirectory,
    navigateToFile,
    getParentDirectory,
  }), [route, navigateHome, navigateToDirectory, navigateToFile, getParentDirectory]);

  return (
    <HashRouterContext.Provider value={value}>
      {children}
    </HashRouterContext.Provider>
  );
}

export function useHashRouter() {
  const context = useContext(HashRouterContext);
  if (!context) throw new Error('useHashRouter deve essere usato dentro HashRouter');
  return context;
}

const FIGURE_GAP = 18;

// Fit the preferred positions in source order. Subtracting the height already
// occupied turns the no-overlap rule into a nondecreasing sequence. When
// neighbors conflict, give them their mean offset so either side can move.
function placeGroups(groups, paddingTop) {
  const runs = [];
  let occupied = paddingTop;
  for (const [start, group] of groups.entries()) {
    group.baseTop = occupied;
    const offset = group.center - group.height / 2 - occupied;
    let run = { start, count: 1, offset };
    while (runs.length && runs.at(-1).offset > run.offset) {
      const previous = runs.pop();
      const count = previous.count + run.count;
      run = {
        start: previous.start,
        count,
        offset: (previous.offset * previous.count + run.offset * run.count) / count,
      };
    }
    runs.push(run);
    occupied += group.height + FIGURE_GAP;
  }

  for (const run of runs) {
    for (let index = run.start; index < run.start + run.count; index++) {
      const { element, baseTop } = groups[index];
      element.style.setProperty("--lesson-figure-top", `${baseTop + Math.max(0, run.offset)}px`);
    }
  }
}

export function alignLessonFigures(article) {
  const groups = Array.from(article.querySelectorAll(":scope > .lesson-figure-row"), (row) => {
    const element = row.querySelector(":scope > .lesson-figures");
    const count = element.querySelectorAll("img").length;
    const prose = row.querySelector(":scope > .lesson-figure-prose")?.children;
    return {
      element,
      count,
      anchor: row.querySelector(":scope > .lesson-figure-anchor > *"),
      firstProse: prose?.[0],
      lastProse: count > 1 ? prose?.[prose.length - 1] : prose?.[1] ?? prose?.[0],
      rule: row.previousElementSibling?.matches("hr") ? row.previousElementSibling : null,
    };
  });
  if (!groups.length) return undefined;

  let frame = 0;
  const align = () => {
    frame = 0;
    if (getComputedStyle(groups[0].element).position !== "absolute") return;

    const origin = article.getBoundingClientRect().top + article.clientTop - article.scrollTop;
    const paddingTop = parseFloat(getComputedStyle(article).paddingTop);
    const previewMax = Math.max(190, Math.min(360, article.clientHeight * 0.48));
    const previewMin = Math.min(190, article.clientWidth * 0.18);

    for (const group of groups) {
      const anchor = (group.anchor || group.rule || group.firstProse)?.getBoundingClientRect();
      const related = group.lastProse?.getBoundingClientRect();
      const start = anchor ? Math.max(anchor.top, anchor.bottom - 140) - origin : paddingTop;
      const end = anchor ? Math.max(anchor.bottom, related?.bottom ?? anchor.bottom) - origin : paddingTop;
      const contextHeight = group.count > 1 ? previewMax * group.count : 220;
      group.center = start + Math.min(contextHeight, end - start) / 2;
    }

    // Keep tall or dense groups close to their source. The full-size image
    // remains available on click, and previews always retain their aspect ratio.
    for (const [index, group] of groups.entries()) {
      const previous = groups[index - 1]?.center ?? group.center - previewMax;
      const next = groups[index + 1]?.center ?? group.center + previewMax;
      const height = Math.max(previewMin, Math.min(previewMax, (next - previous) / (2 * group.count) - FIGURE_GAP));
      group.element.style.setProperty("--lesson-figure-height", `${Math.round(height)}px`);
    }

    for (const group of groups) group.height = group.element.getBoundingClientRect().height;
    placeGroups(groups, paddingTop);
  };

  const schedule = () => {
    if (!frame) frame = requestAnimationFrame(align);
  };

  const observer = new ResizeObserver(schedule);
  observer.observe(article);
  for (const { element, anchor, firstProse, lastProse } of groups) {
    // The wrappers use display: contents on mobile and have no box there.
    observer.observe(element);
    if (anchor) observer.observe(anchor);
    if (firstProse) observer.observe(firstProse);
    if (lastProse) observer.observe(lastProse);
  }
  article.addEventListener("load", schedule, true);
  article.addEventListener("error", schedule, true);
  align();

  return () => {
    cancelAnimationFrame(frame);
    observer.disconnect();
    article.removeEventListener("load", schedule, true);
    article.removeEventListener("error", schedule, true);
  };
}

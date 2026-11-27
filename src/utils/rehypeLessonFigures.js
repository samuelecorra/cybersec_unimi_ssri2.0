const isElement = (node, tagName) => node?.type === "element" && node.tagName === tagName;

const isWhitespace = (node) => node.type === "text" && !node.value.trim();

function isImage(node) {
  if (isElement(node, "img")) return true;
  if (!isElement(node, "a")) return false;
  const children = node.children.filter((child) => !isWhitespace(child));
  return children.length === 1 && isElement(children[0], "img");
}

function isImageBlock(node) {
  if (!isElement(node, "p")) return false;
  const children = node.children.filter((child) => !isWhitespace(child) && !isElement(child, "br"));
  return children.length > 0 && children.every(isImage);
}

function group(className, children) {
  return { type: "element", tagName: "div", properties: { className: [className] }, children };
}

function isSectionBoundary(node) {
  return node?.type === "element" && /^(h[1-6]|hr)$/.test(node.tagName);
}

export default function rehypeLessonFigures() {
  return (tree) => {
    if (!tree.children.some(isImageBlock)) return;

    const blocks = tree.children.filter((node) => !isWhitespace(node) && !(node.type === "raw" && !node.value.trim()));
    const rows = [];
    let index = 0;

    while (index < blocks.length) {
      const block = blocks[index];
      const startsWithImage = isImageBlock(block);
      if (!startsWithImage && !isImageBlock(blocks[index + 1])) {
        rows.push(block);
        index++;
        continue;
      }

      // Align each run with the immediately preceding block. The DOM keeps
      // the original order so the same markup can also be read in one column.
      const children = [];
      if (!startsWithImage) {
        if (isElement(block, "hr")) rows.push(block);
        else children.push(group("lesson-figure-anchor", [block]));
        index++;
      }

      const images = [];
      while (isImageBlock(blocks[index])) images.push(blocks[index++]);
      children.push(group("lesson-figures", images));

      // Let nearby prose fill the space next to a tall image, but leave
      // headings and the anchor of the next image group in their own rows.
      const prose = [];
      while (index < blocks.length && !isSectionBoundary(blocks[index]) && !isImageBlock(blocks[index + 1])) {
        prose.push(blocks[index++]);
      }
      if (prose.length) children.push(group("lesson-figure-prose", prose));
      rows.push(group("lesson-figure-row", children));
    }

    tree.children = rows;
  };
}

import { visit } from "unist-util-visit";

function stripHtmlComments(input) {
  let output = "";
  let cursor = 0;

  while (cursor < input.length) {
    const start = input.indexOf("<!--", cursor);
    if (start === -1) {
      output += input.slice(cursor);
      break;
    }

    output += input.slice(cursor, start);
    const end = input.indexOf("-->", start + 4);
    if (end === -1) break;
    cursor = end + 3;
  }

  // Removing a comment can join text on either side into a new comment opener.
  // Escape any such boundary so downstream HTML handling cannot interpret it.
  return output.replace(/<!--/g, "&lt;!--");
}

// Keep source/visual traceability in Markdown files without displaying it.
// Work on HTML nodes so fenced code and inline code examples stay intact.
export default function remarkSourceComments() {
  return (tree) => {
    visit(tree, "html", (node) => {
      node.value = stripHtmlComments(node.value);
    });
  };
}

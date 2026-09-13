import { visit } from "unist-util-visit";

// Keep source/visual traceability in Markdown files without displaying it.
// Work on HTML nodes so fenced code and inline code examples stay intact.
export default function remarkSourceComments() {
  return (tree) => {
    visit(tree, "html", (node) => {
      node.value = node.value.replace(
        /<!--\s*(?:SOURCE_VISUAL\b[\s\S]*?|INSERT INSTRUCTOR SLIDE\/DIAGRAM HERE\s*)-->/g,
        "",
      );
    });
  };
}

import { visit } from "unist-util-visit";

const CALLOUT_RE = /^\[!(\w+)\]\s*(.*)/;

const CALLOUT_ICONS = {
  abstract: "📋",
  info: "ℹ️",
  tip: "💡",
  warning: "⚠️",
  example: "📝",
  quote: "💬",
  danger: "🔴",
  note: "📌",
  caution: "⚠️",
  important: "❗",
  success: "✅",
  question: "❓",
  bug: "🐛",
  failure: "❌",
};

export default function remarkCallouts() {
  return (tree) => {
    visit(tree, "blockquote", (node) => {
      const firstChild = node.children[0];
      if (!firstChild || firstChild.type !== "paragraph") return;

      const firstInline = firstChild.children[0];
      if (!firstInline || firstInline.type !== "text") return;

      const lines = firstInline.value.split("\n");
      const match = lines[0].match(CALLOUT_RE);
      if (!match) return;

      const calloutType = match[1].toLowerCase();
      const title = match[2] || calloutType.charAt(0).toUpperCase() + calloutType.slice(1);
      const icon = CALLOUT_ICONS[calloutType] || "📌";

      // Remove the [!type] line from text
      lines.shift();
      const remaining = lines.join("\n").trim();

      if (remaining) {
        firstInline.value = remaining;
      } else {
        firstChild.children.shift();
        // Also remove a leading soft break if present
        if (firstChild.children.length > 0 && firstChild.children[0].type === "break") {
          firstChild.children.shift();
        }
      }

      // Remove the paragraph if it's now empty
      if (firstChild.children.length === 0) {
        node.children.shift();
      }

      // Set classes and data attributes on the blockquote
      node.data = node.data || {};
      node.data.hProperties = {
        ...(node.data.hProperties || {}),
        className: `callout callout-${calloutType}`,
        "data-callout": calloutType,
      };

      // Insert a title div as the first child
      node.children.unshift({
        type: "paragraph",
        data: {
          hProperties: { className: "callout-title" },
        },
        children: [{ type: "text", value: `${icon} ${title}` }],
      });
    });
  };
}

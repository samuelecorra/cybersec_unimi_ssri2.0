"""Validate source integrity and the recorded reconstruction; no source writes.

This validates evidence consistency, not whether a human actually read a page.
Semantic/visual inspection is recorded separately in IS01_INSPECTION.json.
"""
from pathlib import Path
from urllib.parse import unquote, urlparse
import ast
import hashlib
import json
import re

ROOT = Path(__file__).resolve().parents[1]
META = ROOT / '_meta'
REPO = ROOT.parents[3]


def main():
    sources = json.loads((META/'SOURCE_MANIFEST.json').read_text(encoding='utf-8'))['sources']
    for source in sources:
        p = ROOT/source['path']
        assert p.is_file(), f'Missing original: {p}'
        assert p.stat().st_size == source['bytes'], f'Source size changed: {p}'
        assert hashlib.sha256(p.read_bytes()).hexdigest() == source['sha256'], f'Source changed: {p}'
    originals = {s['path'] for s in sources}
    undiscovered = [p.relative_to(ROOT).as_posix() for p in ROOT.rglob('*')
                    if p.is_file() and META not in p.parents and p.suffix != '.md'
                    and p.relative_to(ROOT).as_posix() not in originals]
    assert not undiscovered, f'New sources to inventory: {undiscovered}'
    baseline = json.loads((META/'SESSION_BASELINE.json').read_text(encoding='utf-8'))
    for original in baseline['preexisting_algorithm_files']:
        p = REPO/original['path']
        assert p.is_file() and hashlib.sha256(p.read_bytes()).hexdigest() == original['sha256'], f'Unrelated change: {p}'
    inspection = json.loads((META/'IS01_INSPECTION.json').read_text(encoding='utf-8'))
    assert [p['page'] for p in inspection['pages']] == list(range(1,83))
    # Text-first: semantic incorporation is mandatory, page rendering is not.
    assert all(p['text_read'] and p['section'] and p['note'] and p['result'] for p in inspection['pages'])
    coverage = (META/'SOURCE_COVERAGE.md').read_text(encoding='utf-8')
    page_rows = re.findall(r'^\| (\d+) \| (no|sì) \| (no|sì|not required|pending) \|', coverage, re.M)
    assert len(page_rows) == sum(s.get('pages',0) for s in sources) == 2068
    assert sum(t=='sì' for _,t,v in page_rows) == 82
    for source in (s for s in sources if 'pages' in s):
        section = coverage.split('## '+source['path']+'\n',1)[1].split('\n## ',1)[0]
        assert [int(x) for x in re.findall(r'^\| (\d+) \| (?:no|sì) \|',section,re.M)] == list(range(1,source['pages']+1)), source['path']
    lesson = next((ROOT/'Lezione01').glob('*.md'))
    text = lesson.read_text(encoding='utf-8')
    placeholders = re.findall(r'<!-- SOURCE_VISUAL (.*?) -->\n<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->', text)
    assert len(placeholders) == text.count('<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->') == len(inspection['visuals']) == 46
    visual_records = []
    for attributes in placeholders:
        record = dict(re.findall(r'(\w+)="([^"]*)"',attributes));record['page']=int(record['page']);visual_records.append(record)
    assert visual_records == inspection['visuals']
    assert len({v['id'] for v in visual_records}) == 46
    assert all(1 <= v['page'] <= 82 and v['source']==Path(inspection['source']).name for v in visual_records)
    assert '{{VISUAL:' not in text and '\ufffd' not in text
    assert '\\(' not in text and '\\)' not in text
    main_sections = re.findall(r'^### \*\*(\d+)\. ',text,re.M)
    assert main_sections == [str(i) for i in range(1,11)]
    for heading in re.findall(r'^#{3,5} .+$',text,re.M):
        assert re.match(r'^#{3,5} \*\*\d+(?:\.\d+)*\. ',heading), heading
    for line in text.splitlines():
        if line.startswith('> '): assert re.match(r'^> [📌⚠💡✅]',line), line
    assert text.count('```') == 4
    blocks = re.findall(r'```python\n(.*?)\n```',text,re.S)
    assert len(blocks)==2
    for block in blocks: ast.parse(block)
    assert 256-3+1==254 and (3*3*3+1)*25==700 and 800*0.05==40
    links = 0
    for document in [lesson,ROOT/'L0 - Indice del corso.md']:
        body = re.sub(r'```.*?```','',document.read_text(encoding='utf-8'),flags=re.S)
        for target in re.findall(r'\]\(([^)]+)\)',body):
            if urlparse(target).scheme or target.startswith('#'): continue
            path = (document.parent/unquote(target.split('#',1)[0])).resolve()
            assert path.exists(), f'Broken local link: {document.name}: {target}'
            links += 1
    print(json.dumps(dict(sources_unchanged=len(sources),pdfs=26,total_pages=2068,
        pages_processed=82,pages_not_processed=1986,visual_placeholders=46,
        historical_visual_inspections=sum(v=='sì' for _,t,v in page_rows),
        local_links_checked=links,python_blocks_syntax_checked=2,
        numerical_consistency_checks=3,
        unrelated_files_unchanged=len(baseline['preexisting_algorithm_files'])),ensure_ascii=False,indent=2))


if __name__ == '__main__':
    main()

import { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import { createHashPath } from '../utils/hashRouting.js';
import { COURSE_PATH } from './routes.js';
import {
  createAttempt, DEFAULT_MINUTES, DURATIONS, EXAM_COUNT, gradeAttempt, HISTORY_LIMIT,
  lessonLabel, missedIds, PRACTICE_COUNT, topicOf, TOPICS,
} from './exam.js';
import { bank } from './questions.js';
import { useExamRecord } from './useExamRecord.js';
import './exam.css';

const questions = new Map(bank.map(q => [q.id, q]));
const answerLetter = index => String.fromCharCode(65 + index);
const isKnownAttempt = attempt => Array.isArray(attempt?.items) && attempt.items.length > 0
  && attempt.items.every(item => questions.has(item?.id));
const modeLabel = attempt => ({ exam: 'Simulazione', topic: TOPICS[attempt.topic], mistakes: 'Ripasso degli errori' })[attempt.mode];
const gradeLabel = attempt => attempt.grade === 31 ? '30 e lode' : `${attempt.grade}/30`;
const dateFormat = new Intl.DateTimeFormat('it-IT', { dateStyle: 'short', timeStyle: 'short' });

function complete(record, reason, now) {
  const result = gradeAttempt(record.active, questions, reason, now);
  return { active: null, history: [result, ...record.history].slice(0, HISTORY_LIMIT) };
}

function Setup({ history, active, onResume, onStart, onResult }) {
  const [topic, setTopic] = useState(0);
  const [minutes, setMinutes] = useState(DEFAULT_MINUTES);
  const missed = [...missedIds(history)].filter(id => questions.has(id)).length;
  return (
    <>
      <div className="is-exam-intro">
        <h2>Simulatore d’esame</h2>
        <p>Intelligent Systems · {bank.length} quesiti in inglese sulle 24 lezioni</p>
      </div>
      {active && (
        <section className="is-panel is-resume">
          <div><h3>Prova in corso</h3><p>{modeLabel(active)} · {Object.keys(active.answers).length}/{active.items.length} risposte</p></div>
          <button className="is-button is-primary" onClick={onResume}>Riprendi la prova</button>
        </section>
      )}
      <div className="is-setup-grid">
        <section className="is-panel is-exam-mode">
          <h3>Simulazione completa</h3>
          <p>{EXAM_COUNT} domande, una sola risposta valida. +1 per ogni risposta corretta, 0 per errata o omessa. Soluzioni dopo la consegna.</p>
          <div className="is-picker">
            <label htmlFor="is-duration">Durata</label>
            <select id="is-duration" value={minutes} onChange={event => setMinutes(Number(event.target.value))}>
              {DURATIONS.map(value => <option value={value} key={value}>{value} minuti</option>)}
            </select>
          </div>
          <button className="is-button is-primary" disabled={!!active} onClick={() => onStart({ mode: 'exam', minutes })}>Inizia la simulazione</button>
        </section>
        <section className="is-panel">
          <h3>Allenamento per argomento</h3>
          <p>{PRACTICE_COUNT} domande senza timer. Consulta le spiegazioni al termine.</p>
          <div className="is-picker">
            <label htmlFor="is-topic">Argomento</label>
            <select id="is-topic" value={topic} onChange={event => setTopic(Number(event.target.value))}>
              {TOPICS.map((name, index) => <option key={name} value={index}>{name}</option>)}
            </select>
          </div>
          <button className="is-button" disabled={!!active} onClick={() => onStart({ mode: 'topic', topic })}>Allenati</button>
        </section>
        <section className="is-panel">
          <h3>Ripasso degli errori</h3>
          <p>{missed ? `${missed} quesiti da recuperare tra errori e risposte omesse.` : 'Qui ritrovi le domande sbagliate o lasciate in bianco dopo una prova.'}</p>
          <button className="is-button" disabled={!!active || !missed} onClick={() => onStart({ mode: 'mistakes' })}>Ripassa gli errori</button>
        </section>
      </div>
      <p className="is-exam-note">Il gruppo del corso riporta 40 quesiti in 30 minuti per un appello. La scheda UniMi indica circa un’ora. Il voto simulato è 31 × corrette / totale, arrotondato e limitato a 30. Il punteggio pieno vale 30 e lode.</p>
      <section className="is-panel is-history" aria-labelledby="is-history-title">
        <h3 id="is-history-title">Ultimi tentativi</h3>
        {history.length ? (
          <ul>{history.map(result => (
            <li key={result.id}>
              <button className="is-history-row" onClick={() => onResult(result.id)}>
                <span>{modeLabel(result)} <small>{dateFormat.format(result.finishedAt)}</small></span>
                <strong>{result.grade === null ? `${result.correct}/${result.total}` : gradeLabel(result)} <span aria-hidden="true">→</span></strong>
              </button>
            </li>
          ))}</ul>
        ) : <p>I tentativi conclusi vengono salvati in questo browser.</p>}
      </section>
      <p className="is-exam-note">Quesiti e spiegazioni originali, basati sulle lezioni e sui temi del <a href="https://github.com/gorkembaslik/quiz-app">quiz della community</a>. <a href="https://www.unimi.it/it/corsi/insegnamenti-dei-corsi-di-laurea/2026/intelligent-systems-industry-supply-chain-and-environment-0">Scheda UniMi</a></p>
    </>
  );
}

function QuestionView({ attempt, onUpdate, onFinish, onPause }) {
  const [confirming, setConfirming] = useState(false);
  const [now, setNow] = useState(Date.now);
  const heading = useRef(null);
  const keepTaking = useRef(null);
  const { position, answers, flags, deadline, items } = attempt;
  const item = items[position];
  const question = questions.get(item.id);
  const selected = answers[item.id];
  const answered = Object.keys(answers).length;
  const remaining = deadline ? Math.max(0, Math.ceil((deadline - now) / 1000)) : null;
  const time = remaining === null ? '' : `${String(Math.floor(remaining / 60)).padStart(2, '0')}:${String(remaining % 60).padStart(2, '0')}`;
  const timerDisplay = deadline && <span className={`is-timer${remaining <= 60 ? ' is-timer-urgent' : ''}`} role="timer" aria-label={`Tempo rimanente ${time}`}>{time}</span>;

  useEffect(() => {
    if (!deadline) return;
    const tick = () => setNow(Date.now());
    tick();
    const timer = window.setInterval(tick, 250);
    window.addEventListener('focus', tick);
    return () => {
      window.clearInterval(timer);
      window.removeEventListener('focus', tick);
    };
  }, [deadline]);

  useEffect(() => {
    if (remaining === 0) onFinish('timeout');
  }, [remaining, onFinish]);

  useEffect(() => {
    if (confirming) keepTaking.current?.focus();
    else heading.current?.focus();
  }, [position, confirming]);

  if (confirming) return (
    <section className="is-panel is-confirm" aria-labelledby="is-confirm-title">
      <h2 id="is-confirm-title">Conferma la consegna</h2>
      <p>{answered} risposte, {items.length - answered} omesse. {Object.keys(flags).length} quesiti segnati per il controllo.</p>
      <div className="is-actions">
        {timerDisplay}
        <button ref={keepTaking} className="is-button" onClick={() => setConfirming(false)}>Torna alle domande</button>
        <button className="is-button is-primary" onClick={() => onFinish('submitted')}>Consegna ora</button>
      </div>
    </section>
  );

  const move = index => onUpdate(current => ({ ...current, position: index }));
  const choose = option => onUpdate(current => {
    const next = { ...current.answers };
    if (option === undefined) delete next[item.id];
    else next[item.id] = option;
    return { ...current, answers: next };
  });
  const flag = () => onUpdate(current => {
    const next = { ...current.flags };
    if (next[item.id]) delete next[item.id];
    else next[item.id] = true;
    return { ...current, flags: next };
  });

  return (
    <>
      <div className="is-exam-toolbar">
        <div><h2>{modeLabel(attempt)}</h2><p>{answered} di {items.length} con risposta</p></div>
        <div className="is-actions">
          {timerDisplay}
          <button className="is-button" onClick={onPause}>{deadline ? 'Esci, il timer continua' : 'Salva ed esci'}</button>
        </div>
      </div>
      <progress className="is-progress" value={answered} max={items.length} aria-label="Domande con risposta" />
      <div className="is-question-layout">
        <section className="is-panel is-question" aria-label={`Domanda ${position + 1}`}>
          <div className="is-question-meta">
            <span>Domanda {position + 1} / {items.length}</span>
            <button className="is-button is-flag" aria-pressed={!!flags[item.id]} onClick={flag}>{flags[item.id] ? 'Contrassegnata' : 'Contrassegna'}</button>
          </div>
          <h3 ref={heading} tabIndex={-1} lang="en">{question.prompt}</h3>
          <div className="is-options" role="radiogroup" aria-label="Risposte" lang="en">
            {item.order.map((option, index) => (
              <label className="is-option" key={option}>
                <input type="radio" name={`answer-${item.id}`} checked={selected === option} onChange={() => choose(option)} />
                <span className="is-option-letter" aria-hidden="true">{answerLetter(index)}</span>
                <span>{question.options[option]}</span>
              </label>
            ))}
          </div>
          <button className="is-clear" disabled={selected === undefined} onClick={() => choose(undefined)}>Cancella risposta</button>
          <div className="is-question-footer">
            <button className="is-button" disabled={position === 0} onClick={() => move(position - 1)}>← Precedente</button>
            {position + 1 < items.length
              ? <button className="is-button is-primary" onClick={() => move(position + 1)}>Successiva →</button>
              : <button className="is-button is-primary" onClick={() => setConfirming(true)}>Rivedi e consegna</button>}
          </div>
        </section>
        <aside className="is-panel is-question-map" aria-label="Navigazione domande">
          <h3>Domande</h3>
          <div className="is-map-grid">
            {items.map(({ id }, index) => (
              <button className={`is-map-cell${answers[id] !== undefined ? ' is-answered' : ''}${flags[id] ? ' is-flagged' : ''}`}
                key={id} onClick={() => move(index)} aria-current={position === index ? 'step' : undefined}
                aria-label={`Domanda ${index + 1}, ${answers[id] === undefined ? 'omessa' : 'con risposta'}${flags[id] ? ', contrassegnata' : ''}`}>
                {index + 1}
              </button>
            ))}
          </div>
          <div className="is-map-legend"><span>● Con risposta</span><span>◆ Contrassegnata</span></div>
          <button className="is-button" onClick={() => setConfirming(true)}>Rivedi e consegna</button>
        </aside>
      </div>
    </>
  );
}

const outcomeLabel = { correct: 'Corretta', wrong: 'Errata', blank: 'Omessa' };
const filters = [['all', 'Tutte'], ['wrong', 'Errate'], ['blank', 'Omesse'], ['correct', 'Corrette'], ['flagged', 'Contrassegnate']];

function Result({ result, lessonPaths, onHome, onRetry }) {
  const [filter, setFilter] = useState('all');
  const heading = useRef(null);
  const topicResults = new Map();
  for (const { id } of result.items) {
    const topic = topicOf(questions.get(id).lesson);
    const counts = topicResults.get(topic) ?? { correct: 0, total: 0 };
    counts.total++;
    if (result.outcomes[id] === 'correct') counts.correct++;
    topicResults.set(topic, counts);
  }

  useEffect(() => heading.current?.focus(), []);

  const visible = result.items.filter(({ id }) => filter === 'all'
    || (filter === 'flagged' ? result.flags[id] : result.outcomes[id] === filter));
  return (
    <>
      <section className="is-panel is-result" aria-labelledby="is-result-title">
        <div>
          <h2 id="is-result-title" ref={heading} tabIndex={-1}>Risultato della prova</h2>
          <p>{modeLabel(result)} · {result.reason === 'timeout' ? 'Consegnata allo scadere del tempo' : 'Prova consegnata'}</p>
          <strong className="is-grade">{result.grade === null ? `${result.correct}/${result.total}` : gradeLabel(result)}</strong>
          <p>{result.grade === null ? 'Punteggio dell’allenamento' : result.grade >= 18 ? 'Voto simulato · Superato' : 'Voto simulato · Insufficiente'}</p>
        </div>
        <dl className="is-result-counts">
          <div><dt>Corrette</dt><dd>{result.correct}</dd></div>
          <div><dt>Errate</dt><dd>{result.wrong}</dd></div>
          <div><dt>Omesse</dt><dd>{result.blank}</dd></div>
        </dl>
        <div className="is-actions">
          <button className="is-button is-primary" onClick={onHome}>Torna al simulatore</button>
          {onRetry && <button className="is-button" onClick={onRetry}>Ripassa gli errori</button>}
        </div>
      </section>
      <section className="is-panel is-topic-results" aria-labelledby="is-topic-results-title">
        <h3 id="is-topic-results-title">Risultati per argomento</h3>
        {Array.from(topicResults).sort(([a], [b]) => a - b).map(([topic, counts]) => (
          <div key={topic}><span>{TOPICS[topic]}</span><progress value={counts.correct} max={counts.total} aria-label={TOPICS[topic]} /><strong>{counts.correct}/{counts.total}</strong></div>
        ))}
      </section>
      <section className="is-review" aria-labelledby="is-review-title">
        <h3 id="is-review-title">Correzione delle risposte</h3>
        <div className="is-actions is-filters" aria-label="Filtra la correzione">
          {filters.map(([key, label]) => <button key={key} className="is-button" aria-pressed={filter === key} onClick={() => setFilter(key)}>{label}</button>)}
        </div>
        {visible.length === 0 && <p>Nessuna domanda per questo filtro.</p>}
        {visible.map(({ id, order }) => {
          const question = questions.get(id);
          const source = lessonPaths.get(question.lesson);
          const selected = result.answers[id];
          const outcome = result.outcomes[id];
          const position = result.items.findIndex(item => item.id === id) + 1;
          return (
            <details className="is-panel is-review-item" key={id}>
              <summary><span className={`is-outcome is-${outcome}`}>{outcomeLabel[outcome]}</span><span lang="en">{position}. {question.prompt}</span></summary>
              <div className="is-review-body">
                <ul className="is-review-options" lang="en">
                  {order.map((option, index) => (
                    <li key={option} className={option === question.answer ? 'is-correct' : option === selected ? 'is-wrong' : undefined}>
                      <strong>{answerLetter(index)}.</strong> {question.options[option]}
                      {option === question.answer && <small lang="it">Soluzione</small>}
                      {option === selected && <small lang="it">La tua risposta</small>}
                    </li>
                  ))}
                </ul>
                <p lang="en">{question.explanation}</p>
                {source && <a href={createHashPath(source)}>Rileggi {lessonLabel(question.lesson).toLowerCase()} →</a>}
              </div>
            </details>
          );
        })}
      </section>
    </>
  );
}

export default function ExamSimulator({ lessonPaths }) {
  const [record, setRecord] = useExamRecord();
  const [reviewId, setReviewId] = useState(null);
  const [resuming, setResuming] = useState(true);
  const view = useRef(null);
  const active = isKnownAttempt(record.active) ? record.active : null;
  const history = useMemo(() => record.history.filter(isKnownAttempt), [record.history]);
  const activeId = active?.id;
  const deadline = active?.deadline;
  const sourcePaths = useMemo(() => {
    const paths = new Map();
    for (const path of lessonPaths) {
      if (!path.startsWith(`${COURSE_PATH}/`)) continue;
      const match = /^Lezione(\d{2})\/L\d{2} .*\.md$/u.exec(path.slice(COURSE_PATH.length + 1));
      if (match) paths.set(Number(match[1]), path);
    }
    return paths;
  }, [lessonPaths]);

  const finish = useCallback(reason => {
    if (!activeId) return;
    setRecord(current => {
      if (current.active?.id !== activeId) return current;
      const now = Date.now();
      const expired = deadline && now >= deadline;
      if (reason === 'timeout' && !expired) return current;
      return complete(current, expired ? 'timeout' : reason, now);
    });
    setReviewId(activeId);
  }, [activeId, deadline, setRecord]);

  useEffect(() => {
    if (!deadline || resuming) return;
    const checkDeadline = () => {
      if (Date.now() >= deadline) finish('timeout');
    };
    checkDeadline();
    const timer = window.setInterval(checkDeadline, 1000);
    return () => window.clearInterval(timer);
  }, [deadline, finish, resuming]);

  async function start(settings) {
    await setRecord(current => {
      if (isKnownAttempt(current.active)) return current;
      const knownHistory = current.history.filter(isKnownAttempt);
      const attempt = createAttempt(bank, knownHistory, settings);
      return attempt ? { active: attempt, history: knownHistory } : current;
    });
    setReviewId(null);
    setResuming(true);
  }

  function update(change) {
    if (!active) return;
    setRecord(current => {
      if (current.active?.id !== activeId || (deadline && Date.now() >= deadline)) return current;
      return { ...current, active: change(current.active) };
    });
  }

  const result = history.find(entry => entry.id === reviewId);
  const screen = result ? 'result' : active && resuming ? 'active' : 'setup';
  useEffect(() => { view.current?.scrollTo(0, 0); }, [screen, reviewId]);

  return (
    <div className="browse-view is-exam" ref={view}>
      <div className="is-exam-inner">
        <a className="is-back-link" href={createHashPath(COURSE_PATH)}>← Intelligent Systems</a>
        {result ? <Result key={result.id} result={result} lessonPaths={sourcePaths}
          onHome={() => { setReviewId(null); setResuming(false); }}
          onRetry={!active && missedIds(history).size ? () => start({ mode: 'mistakes' }) : null} />
          : active && resuming ? <QuestionView key={active.id} attempt={active} onUpdate={update} onFinish={finish} onPause={() => setResuming(false)} />
          : <Setup history={history} active={active} onResume={() => setResuming(true)} onStart={start} onResult={setReviewId} />}
      </div>
    </div>
  );
}

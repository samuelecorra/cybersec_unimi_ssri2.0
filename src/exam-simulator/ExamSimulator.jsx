import { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import { createHashPath } from '../utils/hashRouting.js';
import {
  completeAttempt, createAttempt, examFormat, lessonLabel, missedIds, orderedActivity, PRACTICE_COUNT, topicOf,
} from './exam.js';
import { useExamRecord } from './useExamRecord.js';
import './exam.css';

const answerLetter = index => String.fromCharCode(65 + index);
const modeLabel = (course, attempt) => ({
  exam: examFormat(course, attempt.format).title ?? 'Simulazione',
  topic: course.topics[attempt.topic],
  mistakes: 'Ripasso degli errori',
})[attempt.mode];
const gradeLabel = attempt => attempt.grade === 31 ? '30 e lode' : `${attempt.grade}/30`;
const dateFormat = new Intl.DateTimeFormat('it-IT', { dateStyle: 'short', timeStyle: 'short' });

function Setup({ course, bankSize, missed, history, active, mistakes, onResume, onStart, onResult }) {
  const [topic, setTopic] = useState(0);
  const [minutes, setMinutes] = useState(course.defaultMinutes);
  const [formatId, setFormatId] = useState(course.formats[0].id);
  const format = examFormat(course, formatId);
  return (
    <>
      <div className="is-exam-intro">
        <h2>Simulatore d’esame</h2>
        <p>{course.title} · {bankSize} quesiti in {course.language} sulle {course.topics.length * 3} lezioni</p>
      </div>
      {active && (
        <section className="is-panel is-resume">
          <div><h3>Prova in corso</h3><p>{modeLabel(course, active)} · {Object.keys(active.answers).length}/{active.items.length} risposte</p></div>
          <button className="is-button is-primary" onClick={onResume}>Riprendi la prova</button>
        </section>
      )}
      <div className="is-setup-grid">
        <section className="is-panel is-exam-mode">
          <h3>Simulazione completa</h3>
          <p>{format.count} domande, una sola risposta valida. +1 per ogni risposta corretta, 0 per errata o omessa. Soluzioni dopo la consegna.</p>
          {course.formats.length > 1 && (
            <div className="is-picker">
              <label htmlFor="is-format">Formato</label>
              <select id="is-format" value={format.id} onChange={event => setFormatId(event.target.value)}>
                {course.formats.map(option => <option key={option.id} value={option.id}>{option.label}</option>)}
              </select>
            </div>
          )}
          <div className="is-picker">
            <label htmlFor="is-duration">Durata</label>
            <select id="is-duration" value={minutes} onChange={event => setMinutes(Number(event.target.value))}>
              {course.durations.map(value => <option value={value} key={value}>{value} minuti</option>)}
            </select>
          </div>
          <button className="is-button is-primary" disabled={!!active} onClick={() => onStart({ mode: 'exam', minutes, format: format.id })}>Inizia la simulazione</button>
        </section>
        <section className="is-panel">
          <h3>Allenamento per argomento</h3>
          <p>{PRACTICE_COUNT} domande senza timer. Consulta le spiegazioni al termine.</p>
          <div className="is-picker">
            <label htmlFor="is-topic">Argomento</label>
            <select id="is-topic" value={topic} onChange={event => setTopic(Number(event.target.value))}>
              {course.topics.map((name, index) => <option key={name} value={index}>{name}</option>)}
            </select>
          </div>
          <button className="is-button" disabled={!!active} onClick={() => onStart({ mode: 'topic', topic })}>Allenati</button>
        </section>
        <section className="is-panel">
          <h3>Ripasso degli errori</h3>
          <p>{mistakes
            ? `Ripasso salvato · ${Object.keys(mistakes.answers).length}/${mistakes.items.length} risposte.`
            : missed ? `${missed} quesiti da recuperare tra errori e risposte omesse.`
              : 'Qui ritrovi le domande sbagliate o lasciate in bianco dopo una prova.'}</p>
          <button className="is-button" disabled={!mistakes && !missed} onClick={() => onStart({ mode: 'mistakes' })}>
            {mistakes ? 'Riprendi il ripasso' : 'Ripassa gli errori'}
          </button>
        </section>
      </div>
      <p className="is-exam-note">{course.formatNote} Il voto simulato è 31 × corrette / totale, arrotondato e limitato a 30. Il punteggio pieno vale 30 e lode.</p>
      <section className="is-panel is-history" aria-labelledby="is-history-title">
        <h3 id="is-history-title">Ultimi tentativi</h3>
        {history.length ? (
          <ul>{history.map(result => (
            <li key={result.id}>
              <button className="is-history-row" onClick={() => onResult(result.id)}>
                <span>{modeLabel(course, result)} <small>{dateFormat.format(result.finishedAt)}</small></span>
                <strong>{result.grade === null ? `${result.correct}/${result.total}` : gradeLabel(result)} <span aria-hidden="true">→</span></strong>
              </button>
            </li>
          ))}</ul>
        ) : <p>I tentativi conclusi vengono salvati in questo browser.</p>}
      </section>
      <p className="is-exam-note">Quesiti e spiegazioni originali, basati sulle lezioni e sui temi discussi dalla community.{' '}
        {course.sources.map(([label, url], index) => (
          <span key={url}>{index > 0 && ' · '}<a href={url}>{label}</a></span>
        ))}
      </p>
    </>
  );
}

function QuestionView({ course, questions, attempt, onUpdate, onFinish, onPause }) {
  const [confirming, setConfirming] = useState(false);
  const [now, setNow] = useState(Date.now);
  const heading = useRef(null);
  const keepTaking = useRef(null);
  const { position, answers, flags, deadline, items } = attempt;
  const reviewing = attempt.mode === 'mistakes';
  const finishLabel = reviewing ? 'Termina il ripasso' : 'Rivedi e consegna';
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
    if (confirming) keepTaking.current?.focus();
    else heading.current?.focus();
  }, [position, confirming]);

  if (confirming) return (
    <section className="is-panel is-confirm" aria-labelledby="is-confirm-title">
      <h2 id="is-confirm-title">{reviewing ? 'Conferma la fine del ripasso' : 'Conferma la consegna'}</h2>
      <p>{answered} risposte, {items.length - answered} omesse. {Object.keys(flags).length} quesiti segnati per il controllo.</p>
      <div className="is-actions">
        {timerDisplay}
        <button ref={keepTaking} className="is-button" onClick={() => setConfirming(false)}>Torna alle domande</button>
        <button className="is-button is-primary" onClick={onFinish}>{reviewing ? 'Concludi il ripasso' : 'Consegna ora'}</button>
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
        <div><h2>{modeLabel(course, attempt)}</h2><p>{answered} di {items.length} con risposta</p></div>
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
          <h3 ref={heading} tabIndex={-1} lang={course.lang}>{question.prompt}</h3>
          <div className="is-options" role="radiogroup" aria-label="Risposte" lang={course.lang} key={item.id}>
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
              : <button className="is-button is-primary" onClick={() => setConfirming(true)}>{finishLabel}</button>}
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
          <button className="is-button" onClick={() => setConfirming(true)}>{finishLabel}</button>
        </aside>
      </div>
    </>
  );
}

const outcomeLabel = { correct: 'Corretta', wrong: 'Errata', blank: 'Omessa' };
const filters = [['all', 'Tutte'], ['wrong', 'Errate'], ['blank', 'Omesse'], ['correct', 'Corrette'], ['flagged', 'Contrassegnate']];

function Result({ course, questions, result, lessonPaths, onHome, onRetry, hasSavedReview }) {
  const [filter, setFilter] = useState('all');
  const heading = useRef(null);
  const target = result.mode === 'exam' ? examFormat(course, result.format).target : undefined;
  const reviewing = result.mode === 'mistakes';
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
          <h2 id="is-result-title" ref={heading} tabIndex={-1}>{reviewing ? 'Riepilogo del ripasso' : 'Risultato della prova'}</h2>
          {!reviewing && <p>{modeLabel(course, result)} · {result.reason === 'timeout' ? 'Consegnata allo scadere del tempo' : 'Prova consegnata'}</p>}
          <strong className="is-grade">{result.grade === null ? `${result.correct}/${result.total}` : gradeLabel(result)}</strong>
          {!reviewing && <p>{result.grade === null ? 'Punteggio dell’allenamento' : target ? 'Voto simulato' : result.grade >= 18 ? 'Voto simulato · Superato' : 'Voto simulato · Insufficiente'}</p>}
          {target && <p>Obiettivo della griglia {result.correct >= target ? 'raggiunto' : 'da raggiungere'} · {target} risposte corrette richieste</p>}
        </div>
        <dl className="is-result-counts">
          <div><dt>Corrette</dt><dd>{result.correct}</dd></div>
          <div><dt>Errate</dt><dd>{result.wrong}</dd></div>
          <div><dt>Omesse</dt><dd>{result.blank}</dd></div>
        </dl>
        <div className="is-actions">
          <button className="is-button is-primary" onClick={onHome}>Torna al simulatore</button>
          {onRetry && <button className="is-button" onClick={onRetry}>{hasSavedReview ? 'Riprendi il ripasso' : 'Ripassa gli errori'}</button>}
        </div>
      </section>
      <section className="is-panel is-topic-results" aria-labelledby="is-topic-results-title">
        <h3 id="is-topic-results-title">Risultati per argomento</h3>
        {Array.from(topicResults).sort(([a], [b]) => a - b).map(([topic, counts]) => (
          <div key={topic}><span>{course.topics[topic]}</span><progress value={counts.correct} max={counts.total} aria-label={course.topics[topic]} /><strong>{counts.correct}/{counts.total}</strong></div>
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
              <summary><span className={`is-outcome is-${outcome}`}>{outcomeLabel[outcome]}</span><span className="is-review-prompt" lang={course.lang}>{position}. {question.prompt}</span></summary>
              <div className="is-review-body">
                <ul className="is-review-options" lang={course.lang}>
                  {order.map((option, index) => (
                    <li key={option} className={option === question.answer ? 'is-correct' : option === selected ? 'is-wrong' : undefined}>
                      <strong>{answerLetter(index)}.</strong> {question.options[option]}
                      {option === question.answer && <small lang="it">Soluzione</small>}
                      {option === selected && <small lang="it">La tua risposta</small>}
                    </li>
                  ))}
                </ul>
                <p lang={course.lang}>{question.explanation}</p>
                {source && <a href={createHashPath(source)}>Rileggi {lessonLabel(question.lesson).toLowerCase()} →</a>}
              </div>
            </details>
          );
        })}
      </section>
    </>
  );
}

export default function ExamSimulator({ course, bank, lessonPaths }) {
  const questions = useMemo(() => new Map(bank.map(q => [q.id, q])), [bank]);
  const isKnownAttempt = useCallback(attempt => Array.isArray(attempt?.items) && attempt.items.length > 0
    && attempt.items.every(item => questions.has(item?.id)), [questions]);
  const [record, setRecord] = useExamRecord(course.storageKey);
  const [reviewId, setReviewId] = useState(null);
  const [selectedSlot, setSelectedSlot] = useState('active');
  const view = useRef(null);
  const active = isKnownAttempt(record.active) ? record.active : null;
  const mistakes = isKnownAttempt(record.mistakes) ? record.mistakes : null;
  const history = useMemo(() => record.history.filter(isKnownAttempt), [record.history, isKnownAttempt]);
  const activity = useMemo(() => orderedActivity(history, record.reviews.filter(isKnownAttempt)),
    [history, record.reviews, isKnownAttempt]);
  const missed = useMemo(() => missedIds(activity).size, [activity]);
  const activeId = active?.id;
  const deadline = active?.deadline;
  const currentAttempt = selectedSlot === 'active' ? active : selectedSlot === 'mistakes' ? mistakes : null;
  const sourcePaths = useMemo(() => {
    const paths = new Map();
    for (const path of lessonPaths) {
      if (!path.startsWith(`${course.path}/`)) continue;
      const match = /^Lezione(\d{2})\/L\d{2} .*\.md$/u.exec(path.slice(course.path.length + 1));
      if (match) paths.set(Number(match[1]), path);
    }
    return paths;
  }, [lessonPaths, course.path]);

  const finish = useCallback((slot, id, reason) => {
    void setRecord(current => completeAttempt(current, slot, id, questions, reason, Date.now()));
  }, [questions, setRecord]);

  useEffect(() => {
    if (!deadline) return;
    const checkDeadline = () => {
      if (Date.now() < deadline) return;
      finish('active', activeId, 'timeout');
      if (selectedSlot !== 'mistakes' && !reviewId) {
        setReviewId(activeId);
        setSelectedSlot(null);
      }
    };
    checkDeadline();
    const timer = window.setInterval(checkDeadline, 1000);
    return () => window.clearInterval(timer);
  }, [activeId, deadline, finish, reviewId, selectedSlot]);

  async function start(settings) {
    const slot = settings.mode === 'mistakes' ? 'mistakes' : 'active';
    await setRecord(current => {
      if (isKnownAttempt(current[slot])) return current;
      const recent = orderedActivity(current.history.filter(isKnownAttempt), current.reviews.filter(isKnownAttempt));
      const attempt = createAttempt(course, bank, recent, settings);
      return attempt ? { ...current, [slot]: attempt } : current;
    });
    setReviewId(null);
    setSelectedSlot(slot);
  }

  function update(change) {
    if (!currentAttempt) return;
    setRecord(current => {
      const attempt = current[selectedSlot];
      if (attempt?.id !== currentAttempt.id || (attempt.deadline && Date.now() >= attempt.deadline)) return current;
      return { ...current, [selectedSlot]: change(attempt) };
    });
  }

  function submit() {
    finish(selectedSlot, currentAttempt.id, 'submitted');
    setReviewId(currentAttempt.id);
  }

  const result = activity.find(entry => entry.id === reviewId);
  const screen = result ? 'result' : currentAttempt ? selectedSlot : 'setup';
  useEffect(() => { view.current?.scrollTo(0, 0); }, [screen, reviewId]);

  return (
    <div className="browse-view is-exam" ref={view}>
      <div className="is-exam-inner">
        <a className="is-back-link" href={createHashPath(course.path)}>← {course.title}</a>
        {result ? <Result key={result.id} course={course} questions={questions} result={result} lessonPaths={sourcePaths}
          onHome={() => { setReviewId(null); setSelectedSlot(null); }}
          hasSavedReview={!!mistakes} onRetry={mistakes || missed ? () => start({ mode: 'mistakes' }) : null} />
          : currentAttempt ? <QuestionView key={currentAttempt.id} course={course} questions={questions} attempt={currentAttempt} onUpdate={update} onFinish={submit} onPause={() => setSelectedSlot(null)} />
          : <Setup course={course} bankSize={bank.length} missed={missed} history={history} active={active} mistakes={mistakes}
            onResume={() => { setReviewId(null); setSelectedSlot('active'); }} onStart={start} onResult={setReviewId} />}
      </div>
    </div>
  );
}

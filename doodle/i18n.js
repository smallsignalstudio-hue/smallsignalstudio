/* Daily Doodle share page strings. Classic script; no modules. */
(function () {
  var STRINGS = {
    en: {
      subtitle: "Daily Doodle",
      pitch1: "Same stage, same 5 units, for everyone on Earth today.",
      pitch2: "Draw your Captain, draft your upgrades, beat your friend's time.",
      store: "Download on the App Store",
      daily_reset: "A new Daily Doodle every day at 00:00 UTC.",
      title: "Doodle Legion — Daily Doodle"
    },
    de: {
      subtitle: "Tages-Doodle",
      pitch1: "Dasselbe Level, dieselben 5 Einheiten, heute für alle auf der Erde.",
      pitch2: "Zeichne deinen Kapitän, wähle deine Upgrades, schlag die Zeit deines Freundes.",
      store: "Laden im App Store",
      daily_reset: "Jeden Tag um 00:00 UTC ein neues Tages-Doodle.",
      title: "Doodle Legion — Tages-Doodle"
    },
    es: {
      subtitle: "Garabato Diario",
      pitch1: "El mismo nivel, las mismas 5 unidades, hoy para todos en la Tierra.",
      pitch2: "Dibuja a tu Capitán, elige tus mejoras, supera el tiempo de tu amigo.",
      store: "Descárgalo en el App Store",
      daily_reset: "Un nuevo Garabato Diario cada día a las 00:00 UTC.",
      title: "Doodle Legion — Garabato Diario"
    },
    fr: {
      subtitle: "Gribouille du jour",
      pitch1: "Le même niveau, les mêmes 5 unités, aujourd'hui pour tout le monde sur Terre.",
      pitch2: "Dessine ton Capitaine, choisis tes améliorations, bats le temps d'un ami.",
      store: "Télécharger dans l'App Store",
      daily_reset: "Chaque jour à 00:00 UTC, une nouvelle Gribouille du jour.",
      title: "Doodle Legion — Gribouille du jour"
    },
    it: {
      subtitle: "Scarabocchio del giorno",
      pitch1: "Lo stesso livello, le stesse 5 unità, oggi per tutti sulla Terra.",
      pitch2: "Disegna il Capitano, scegli i potenziamenti, batti il tempo di un amico.",
      store: "Scarica su App Store",
      daily_reset: "Ogni giorno alle 00:00 UTC, un nuovo Scarabocchio del giorno.",
      title: "Doodle Legion — Scarabocchio del giorno"
    },
    ja: {
      subtitle: "デイリードゥードゥル",
      pitch1: "今日は世界中のみんなが、同じステージ・同じ5ユニットで挑戦。",
      pitch2: "キャプテンを描いて、強化をドラフトして、友だちのタイムに勝とう。",
      store: "App Storeからダウンロード",
      daily_reset: "毎日 00:00 UTC に新しいデイリードゥードゥルが登場。",
      title: "Doodle Legion — デイリードゥードゥル"
    },
    ko: {
      subtitle: "오늘의 낙서",
      pitch1: "오늘은 전 세계 모두가 같은 스테이지, 같은 유닛 5개로 도전해요.",
      pitch2: "대장을 그리고, 강화 카드를 고르고, 친구 기록을 이겨 봐요.",
      store: "App Store에서 다운로드",
      daily_reset: "매일 00:00 UTC에 새로운 오늘의 낙서가 열려요.",
      title: "Doodle Legion — 오늘의 낙서"
    },
    "pt-BR": {
      subtitle: "Rabisco Diário",
      pitch1: "A mesma fase, as mesmas 5 unidades, hoje para todo mundo na Terra.",
      pitch2: "Desenhe seu Capitão, escolha suas melhorias, bata o tempo do seu amigo.",
      store: "Baixar na App Store",
      daily_reset: "Um novo Rabisco Diário todo dia às 00:00 UTC.",
      title: "Doodle Legion — Rabisco Diário"
    },
    ru: {
      subtitle: "Каракуля дня",
      pitch1: "Тот же уровень и те же 5 бойцов — сегодня для всех на Земле.",
      pitch2: "Нарисуй Капитана, выбери улучшения, обгони время друга.",
      store: "Загрузите в App Store",
      daily_reset: "Новая Каракуля дня каждый день в 00:00 UTC.",
      title: "Doodle Legion — Каракуля дня"
    },
    tr: {
      subtitle: "Günün Karalaması",
      pitch1: "Aynı bölüm, aynı 5 birim, bugün Dünya'daki herkes için.",
      pitch2: "Kaptanını çiz, geliştirmelerini seç, arkadaşının süresini geç.",
      store: "App Store'dan indirin",
      daily_reset: "Her gün 00:00 UTC'de yeni bir Günün Karalaması.",
      title: "Doodle Legion — Günün Karalaması"
    }
  };

  function pick(languages) {
    var list = languages || [];
    var i;
    var tag;
    var primary;
    var code;
    for (i = 0; i < list.length; i++) {
      if (typeof list[i] !== "string") continue;
      tag = list[i].toLowerCase();
      for (code in STRINGS) {
        if (Object.prototype.hasOwnProperty.call(STRINGS, code) && code.toLowerCase() === tag) {
          return code;
        }
      }
    }
    for (i = 0; i < list.length; i++) {
      if (typeof list[i] !== "string") continue;
      tag = list[i].toLowerCase();
      primary = tag.split("-")[0];
      if (primary === "pt") return "pt-BR";
      for (code in STRINGS) {
        if (Object.prototype.hasOwnProperty.call(STRINGS, code) && code.toLowerCase() === primary) {
          return code;
        }
      }
    }
    return "en";
  }

  function apply() {
    var list = (typeof navigator !== "undefined" && navigator.languages && navigator.languages.length)
      ? navigator.languages
      : [typeof navigator !== "undefined" ? navigator.language : "en"];
    var code = pick(list);
    var nodes;
    var i;
    var key;
    if (code === "en") return;
    document.documentElement.lang = code;
    document.title = STRINGS[code].title;
    nodes = document.querySelectorAll("[data-i18n]");
    for (i = 0; i < nodes.length; i++) {
      key = nodes[i].getAttribute("data-i18n");
      if (Object.prototype.hasOwnProperty.call(STRINGS[code], key)) {
        nodes[i].textContent = STRINGS[code][key];
      }
    }
  }

  if (typeof document !== "undefined") {
    if (document.readyState === "loading") {
      document.addEventListener("DOMContentLoaded", apply);
    } else {
      apply();
    }
  }

  if (typeof module !== "undefined") {
    module.exports = { pick: pick, STRINGS: STRINGS };
  }
})();

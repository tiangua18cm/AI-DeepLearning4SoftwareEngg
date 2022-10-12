package ch.usi.si.seart.crawler.service;

import ch.usi.si.seart.analyzer.Analyzer;
import ch.usi.si.seart.analyzer.AnalyzerCustomizer;
import ch.usi.si.seart.analyzer.LocalClone;
import ch.usi.si.seart.model.Language;
import ch.usi.si.seart.model.code.File;
import ch.usi.si.seart.model.code.Function;
import ch.usi.si.seart.service.LanguageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.spr
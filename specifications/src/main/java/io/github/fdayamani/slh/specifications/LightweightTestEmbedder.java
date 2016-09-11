package io.github.fdayamani.slh.specifications;

import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;
import static org.junit.Assert.assertThat;

    /**
     * A class to fully encapsulates all of the JBehave plumbing behind a builder style API.  The expected use for this would be:
     * {code}aLightweightTestRunnerWithStepsFrom().usingStepsFrom(this).withStory("your.story").run(){code}
     *
     */
    public final class LightweightTestEmbedder extends ConfigurableEmbedder {

        private static final Logger LOG = LoggerFactory.getLogger(LightweightTestEmbedder.class);

        private String storyPath;
        private InjectableStepsFactory stepsFactory;
        private Class<?> codeLocationClass;


        private LightweightTestEmbedder(Object... stepsSource) {
            codeLocationClass = stepsSource[0].getClass();
            usingStepsFrom(stepsSource);
        }

        public static LightweightTestEmbedder aLightweightTestRunnerWithStepsFrom(Object... stepsSource) {
            return new LightweightTestEmbedder(stepsSource);
        }

        @Override
        public void run() throws Exception {
            LOG.info("Running [" + this.getClass().getSimpleName() + "] with stories [" + storyPath + "]");
            configuredEmbedder().runStoriesAsPaths(asList(storyPath));
        }

        @Override
        public InjectableStepsFactory stepsFactory() {
            assertThat(stepsFactory, is(notNullValue()));
            return stepsFactory;
        }

        public Configuration configuration() {
            return new MostUsefulConfiguration()
                    .useStoryLoader(new LoadFromClasspath(codeLocationClass))
                    .useStoryReporterBuilder(new HeartsStoryReporterBuilder(codeLocationClass));
        }

        public LightweightTestEmbedder withStory(String storyPath) {
            this.storyPath = storyPath;
            return this;
        }

        public LightweightTestEmbedder usingStepsFrom(Object... stepsSource) {
            this.stepsFactory = new InstanceStepsFactory(configuration(), stepsSource);
            return this;
        }

        private class HeartsStoryReporterBuilder extends StoryReporterBuilder {
            public HeartsStoryReporterBuilder(Class<?> codeLocationClass) {
                withCodeLocation(codeLocationFromClass(codeLocationClass));
                withRelativeDirectory("../reports/jbehave");
                withDefaultFormats();
                withFormats(CONSOLE, TXT, HTML_TEMPLATE, XML);
                withFailureTrace(true);
            }
        }
    }
/*
 * © Copyright 2022 Micro Focus or one of its affiliates.
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microfocus.lrc.jenkins;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.RestartableJenkinsRule;
import org.xml.sax.SAXException;

import java.io.IOException;

public class TestRunBuilderGlobalTest {
    @Rule
    public RestartableJenkinsRule rJenkins = new RestartableJenkinsRule();

    private HtmlInput getInput(HtmlForm form, String name) throws IOException, SAXException {
        HtmlElement div = form.getElementsByAttribute("div", "name", "com-microfocus-lrc-jenkins-TestRunBuilder").get(0);
        HtmlElement pDiv = (HtmlElement) div.getParentNode();
        HtmlInput urlInput = (HtmlInput) pDiv.getElementsByAttribute("input", "name", "_." + name).get(0);

        return urlInput;
    }

    // don't know why, but it just doesn't work
    // fields are always null after restarting
    public void testGlobalConfig() {
        rJenkins.then(jenkins -> {
            try (JenkinsRule.WebClient client = jenkins.createWebClient()) {
                HtmlForm form = client.goTo("configure").getFormByName("config");
                HtmlInput urlInput = this.getInput(form, "url");
                urlInput.setValueAttribute("FAKE_URL");
                jenkins.submit(form);
            }
        });

        rJenkins.then(jenkins -> {
            try (JenkinsRule.WebClient client = jenkins.createWebClient()) {
                HtmlForm form = client.goTo("configure").getFormByName("config");
                HtmlInput urlInput = this.getInput(form, "url");
                Assert.assertEquals("FAKE_URL", urlInput.getValueAttribute());
            }
        });
    }
}

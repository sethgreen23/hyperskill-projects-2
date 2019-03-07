package test

import org.assertj.swing.fixture.FrameFixture
import org.hyperskill.hstest.dev.stage.SwingTest
import org.hyperskill.hstest.dev.testcase.CheckResult
import org.hyperskill.hstest.dev.testcase.TestCase
import javax.swing.JFrame

abstract class PublicSwingTest<AttachType>(frame: JFrame) : SwingTest<AttachType>(frame) {
    val frame: JFrame get() = super.frame
    val window: FrameFixture get() = super.window
}

class WebCrawlerClue(val feedback: String, val checker: () -> Boolean)

fun createWebCrawlerTest(feedback: String, checker: () -> Boolean): TestCase<WebCrawlerClue> {
    return TestCase<WebCrawlerClue>()
        .setAttach(
            WebCrawlerClue(
                feedback = feedback,
                checker = checker
            )
        )
}

fun checkWebCrawlerTest(reply: String, clue: WebCrawlerClue): CheckResult {
    return try {
        val result = clue.checker()

        CheckResult(result, clue.feedback)
    } catch (e: AssertionError) {
        CheckResult(false, clue.feedback)
    }
}
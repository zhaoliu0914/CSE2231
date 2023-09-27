import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 *
 * Test case for WordCounter.
 *
 * @author Zhao Liu
 *
 */
public class WordCounterTest {

    @Test
    public void testConvertInputFileToQueue_1() {
        SimpleReader in = new SimpleReader1L("data/gettysburg.txt");
        Queue<String> expectQueue = new Queue2<>();
        expectQueue.enqueue(
                "   Four score and seven years ago our fathers brought forth on this continent");
        expectQueue.enqueue(
                "a new nation, conceived in liberty, and dedicated to the proposition that all");
        expectQueue.enqueue("men are created equal.");
        expectQueue.enqueue("");
        expectQueue.enqueue(
                "   Now we are engaged in a great civil war, testing whether that nation, or");
        expectQueue.enqueue(
                "any nation, so conceived and so dedicated, can long endure. We are met on a");
        expectQueue.enqueue(
                "great battle-field of that war. We have come to dedicate a portion of that");
        expectQueue.enqueue(
                "field, as a final resting place for those who here gave their lives that that");
        expectQueue.enqueue(
                "nation might live. It is altogether fitting and proper that we should do this.");
        expectQueue.enqueue("");
        expectQueue.enqueue(
                "   But, in a larger sense, we can not dedicate, we can not consecrate, we can");
        expectQueue.enqueue(
                "not hallow this ground. The brave men, living and dead, who struggled here,");
        expectQueue.enqueue(
                "have consecrated it, far above our poor power to add or detract. The world");
        expectQueue.enqueue(
                "will little note, nor long remember what we say here, but it can never forget");
        expectQueue.enqueue(
                "what they did here. It is for us the living, rather, to be dedicated here to");
        expectQueue.enqueue(
                "the unfinished work which they who fought here have thus far so nobly");
        expectQueue.enqueue(
                "advanced. It is rather for us to be here dedicated to the great task");
        expectQueue.enqueue(
                "remaining before us--that from these honored dead we take increased devotion");
        expectQueue.enqueue(
                "to that cause for which they gave the last full measure of devotion--that we");
        expectQueue.enqueue(
                "here highly resolve that these dead shall not have died in vain--that this");
        expectQueue.enqueue(
                "nation, under God, shall have a new birth of freedom--and that government of");
        expectQueue.enqueue(
                "the people, by the people, for the people, shall not perish from the earth.");

        Queue<String> resultQueue = WordCounter.convertInputFileToQueue(in);

        // close stream
        in.close();

        assertEquals(expectQueue, resultQueue);
    }

    @Test
    public void testCountWords_1() {
        Queue<String> contentQueue = new Queue2<>();
        contentQueue.enqueue(
                "   Four score and seven years ago our fathers brought forth on this continent");
        contentQueue.enqueue(
                "a new nation, conceived in liberty, and dedicated to the proposition that all");
        contentQueue.enqueue("men are created equal.");
        contentQueue.enqueue("");
        contentQueue.enqueue(
                "   Now we are engaged in a great civil war, testing whether that nation, or");
        contentQueue.enqueue(
                "any nation, so conceived and so dedicated, can long endure. We are met on a");
        contentQueue.enqueue(
                "great battle-field of that war. We have come to dedicate a portion of that");
        contentQueue.enqueue(
                "field, as a final resting place for those who here gave their lives that that");
        contentQueue.enqueue(
                "nation might live. It is altogether fitting and proper that we should do this.");
        contentQueue.enqueue("");
        contentQueue.enqueue(
                "   But, in a larger sense, we can not dedicate, we can not consecrate, we can");
        contentQueue.enqueue(
                "not hallow this ground. The brave men, living and dead, who struggled here,");
        contentQueue.enqueue(
                "have consecrated it, far above our poor power to add or detract. The world");
        contentQueue.enqueue(
                "will little note, nor long remember what we say here, but it can never forget");
        contentQueue.enqueue(
                "what they did here. It is for us the living, rather, to be dedicated here to");
        contentQueue.enqueue(
                "the unfinished work which they who fought here have thus far so nobly");
        contentQueue.enqueue(
                "advanced. It is rather for us to be here dedicated to the great task");
        contentQueue.enqueue(
                "remaining before us--that from these honored dead we take increased devotion");
        contentQueue.enqueue(
                "to that cause for which they gave the last full measure of devotion--that we");
        contentQueue.enqueue(
                "here highly resolve that these dead shall not have died in vain--that this");
        contentQueue.enqueue(
                "nation, under God, shall have a new birth of freedom--and that government of");
        contentQueue.enqueue(
                "the people, by the people, for the people, shall not perish from the earth.");

        Map<String, Integer> expectMap = new Map1L<>();
        expectMap.add("a", 7);
        expectMap.add("above", 1);
        expectMap.add("add", 1);
        expectMap.add("advanced", 1);
        expectMap.add("ago", 1);
        expectMap.add("all", 1);
        expectMap.add("altogether", 1);
        expectMap.add("and", 6);
        expectMap.add("any", 1);
        expectMap.add("are", 3);
        expectMap.add("as", 1);
        expectMap.add("battle", 1);
        expectMap.add("be", 2);
        expectMap.add("before", 1);
        expectMap.add("birth", 1);
        expectMap.add("brave", 1);
        expectMap.add("brought", 1);
        expectMap.add("but", 1);
        expectMap.add("But", 1);
        expectMap.add("by", 1);
        expectMap.add("can", 5);
        expectMap.add("cause", 1);
        expectMap.add("civil", 1);
        expectMap.add("come", 1);
        expectMap.add("conceived", 2);
        expectMap.add("consecrate", 1);
        expectMap.add("consecrated", 1);
        expectMap.add("continent", 1);
        expectMap.add("created", 1);
        expectMap.add("dead", 3);
        expectMap.add("dedicate", 2);
        expectMap.add("dedicated", 4);
        expectMap.add("detract", 1);
        expectMap.add("devotion", 2);
        expectMap.add("did", 1);
        expectMap.add("died", 1);
        expectMap.add("do", 1);
        expectMap.add("earth", 1);
        expectMap.add("endure", 1);
        expectMap.add("engaged", 1);
        expectMap.add("equal", 1);
        expectMap.add("far", 2);
        expectMap.add("fathers", 1);
        expectMap.add("field", 2);
        expectMap.add("final", 1);
        expectMap.add("fitting", 1);
        expectMap.add("for", 5);
        expectMap.add("forget", 1);
        expectMap.add("forth", 1);
        expectMap.add("fought", 1);
        expectMap.add("Four", 1);
        expectMap.add("freedom", 1);
        expectMap.add("from", 2);
        expectMap.add("full", 1);
        expectMap.add("gave", 2);
        expectMap.add("God", 1);
        expectMap.add("government", 1);
        expectMap.add("great", 3);
        expectMap.add("ground", 1);
        expectMap.add("hallow", 1);
        expectMap.add("have", 5);
        expectMap.add("here", 8);
        expectMap.add("highly", 1);
        expectMap.add("honored", 1);
        expectMap.add("in", 4);
        expectMap.add("increased", 1);
        expectMap.add("is", 3);
        expectMap.add("it", 2);
        expectMap.add("It", 3);
        expectMap.add("larger", 1);
        expectMap.add("last", 1);
        expectMap.add("liberty", 1);
        expectMap.add("little", 1);
        expectMap.add("live", 1);
        expectMap.add("lives", 1);
        expectMap.add("living", 2);
        expectMap.add("long", 2);
        expectMap.add("measure", 1);
        expectMap.add("men", 2);
        expectMap.add("met", 1);
        expectMap.add("might", 1);
        expectMap.add("nation", 5);
        expectMap.add("never", 1);
        expectMap.add("new", 2);
        expectMap.add("nobly", 1);
        expectMap.add("nor", 1);
        expectMap.add("not", 5);
        expectMap.add("note", 1);
        expectMap.add("Now", 1);
        expectMap.add("of", 5);
        expectMap.add("on", 2);
        expectMap.add("or", 2);
        expectMap.add("our", 2);
        expectMap.add("people", 3);
        expectMap.add("perish", 1);
        expectMap.add("place", 1);
        expectMap.add("poor", 1);
        expectMap.add("portion", 1);
        expectMap.add("power", 1);
        expectMap.add("proper", 1);
        expectMap.add("proposition", 1);
        expectMap.add("rather", 2);
        expectMap.add("remaining", 1);
        expectMap.add("remember", 1);
        expectMap.add("resolve", 1);
        expectMap.add("resting", 1);
        expectMap.add("say", 1);
        expectMap.add("score", 1);
        expectMap.add("sense", 1);
        expectMap.add("seven", 1);
        expectMap.add("shall", 3);
        expectMap.add("should", 1);
        expectMap.add("so", 3);
        expectMap.add("struggled", 1);
        expectMap.add("take", 1);
        expectMap.add("task", 1);
        expectMap.add("testing", 1);
        expectMap.add("that", 13);
        expectMap.add("The", 2);
        expectMap.add("the", 9);
        expectMap.add("their", 1);
        expectMap.add("these", 2);
        expectMap.add("they", 3);
        expectMap.add("this", 4);
        expectMap.add("those", 1);
        expectMap.add("thus", 1);
        expectMap.add("to", 8);
        expectMap.add("under", 1);
        expectMap.add("unfinished", 1);
        expectMap.add("us", 3);
        expectMap.add("vain", 1);
        expectMap.add("war", 2);
        expectMap.add("We", 2);
        expectMap.add("we", 8);
        expectMap.add("what", 2);
        expectMap.add("whether", 1);
        expectMap.add("which", 2);
        expectMap.add("who", 3);
        expectMap.add("will", 1);
        expectMap.add("work", 1);
        expectMap.add("world", 1);
        expectMap.add("years", 1);

        Map<String, Integer> resultMap = WordCounter.countWords(contentQueue);

        assertEquals(expectMap, resultMap);

    }

}

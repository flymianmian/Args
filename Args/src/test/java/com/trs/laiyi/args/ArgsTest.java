package com.trs.laiyi.args;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ArgsTest {
    @Test
    public void overAll() throws ArgsException {
        //Step 1 parse text
        String argsAsText = "-l -p 8080 -d /usr/logs";
        List<String> argsAsArray = ArgsParser.parseArgsAsText(argsAsText);
//        assertThat(argsAsArray).isEqualTo(Arrays.asList("-l", "-p:8080", "-d:/usr/logs"));

        //Step 2 parse schema
        String schemaAsText = "l:boolean p:integer d:string";
        Schema schema = new Schema(schemaAsText);
//        assertThat(schema.getDefinition("l")).isEqualTo("boolean");

        //Step 3 get args value
        Args args = new Args(schema, argsAsArray);
        assertThat(args.getValue("l")).isEqualTo(true);
    }

    @Test
    public void testParseArgsAsText() throws ArgsException {
        assertThat(ArgsParser.parseArgsAsText("-l -p 8080 -d /usr/logs"))
                .isEqualTo(Arrays.asList("-l", "-p:8080", "-d:/usr/logs"));
        assertThatExceptionOfType(ArgsException.class)
                .isThrownBy(() -> ArgsParser.parseArgsAsText(""))
                .withMessage("can not parse empty text!");
    }

    @Test
    public void testParseSchema() throws ArgsException {
        assertThat(new Schema("l:boolean").getDefinition("l")).isEqualTo("boolean");
        assertThat(new Schema("p:integer").getDefinition("p")).isEqualTo("integer");
        assertThat(new Schema("d:string").getDefinition("d")).isEqualTo("string");
        assertThat(new Schema("l:boolean      p:integer").getDefinition("l")).isEqualTo("boolean");
        Schema schema = new Schema("l:boolean p:integer d:string");
        assertThat(schema.getDefinition("l")).isEqualTo("boolean");
        assertThat(schema.getDefinition("p")).isEqualTo("integer");
        assertThat(schema.getDefinition("d")).isEqualTo("string");
        assertThatExceptionOfType(ArgsException.class).isThrownBy(() -> new Schema("")).withMessage("can not parse empty text!");
    }

    @Test
    public void testParseArgs() throws ArgsException {
        Schema schema = new Schema("l:boolean p:integer d:string");
        assertThat(new Args(schema, Collections.singletonList("-l")).getValue("l")).isEqualTo(true);
        assertThat(new Args(schema, Collections.singletonList("-p 8080")).getValue("p")).isEqualTo(8080);
        assertThat(new Args(schema, Collections.singletonList("-d /usr/logs")).getValue("d")).isEqualTo("/usr/logs");
        Args args = new Args(schema, Arrays.asList("-l", "-p 8080", "-d /usr/logs"));
        assertThat(args.getValue("l")).isNotNull().isEqualTo(true);
        assertThat(args.getValue("p")).isEqualTo(8080);
        assertThat(args.getValue("d")).isEqualTo("/usr/logs");
        assertThatExceptionOfType(ArgsException.class).isThrownBy(() -> args.getValue("a")).withMessage("no such arg 'a'!");
    }
}
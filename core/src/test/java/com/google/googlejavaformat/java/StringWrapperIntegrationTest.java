/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.googlejavaformat.java;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.truth.Truth.assertThat;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** {@link StringWrapper}IntegrationTest */
@RunWith(Parameterized.class)
public class StringWrapperIntegrationTest {

  @Parameters
  public static Collection<Object[]> parameters() {
    String[][][] inputsAndOutputs = {
      {
        {
          "class T {", //
          "  String s =",
          "      \"one long incredibly unbroken sentence\"",
          "          + \" moving from topic to topic\"",
          "          + \" so that no-one had a chance to\"",
          "          + \" interrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"one long incredibly unbroken\"",
          "\t\t\t+ \" sentence moving from topic\"",
          "\t\t\t+ \" to topic so that no-one had\"",
          "\t\t\t+ \" a chance to interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s =",
          "      \"one long incredibly unbroken\"",
          "          + \" sentence moving from topic to topic so that\"",
          "          + \" no-one had a chance to\"",
          "          + \" interrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"one long incredibly unbroken\"",
          "\t\t\t+ \" sentence moving from topic\"",
          "\t\t\t+ \" to topic so that no-one had\"",
          "\t\t\t+ \" a chance to interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s =",
          "      \"one long incredibly unbroken\"",
          "          + \" sentence moving from topic to topic\"",
          "          + \" so that no-one had a chance to interr\"",
          "          + \"upt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"one long incredibly unbroken\"",
          "\t\t\t+ \" sentence moving from topic\"",
          "\t\t\t+ \" to topic so that no-one had\"",
          "\t\t\t+ \" a chance to interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s = \"one long incredibly unbroken sentence moving from topic to topic so that"
              + " no-one had a chance to interrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"one long incredibly unbroken\"",
          "\t\t\t+ \" sentence moving from topic\"",
          "\t\t\t+ \" to topic so that no-one had\"",
          "\t\t\t+ \" a chance to interrupt\";",
          "}",
        },
      },
      {
        {
          "class T {", //
          "  String s =",
          "      \"one long incredibly unbroken sentence\"",
          "          + \" moving from topic to topic\"",
          "          + 42",
          "          + \" so that no-one had a chance to interr\"",
          "          + \"upt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"one long incredibly unbroken\"",
          "\t\t\t+ \" sentence moving from topic\"",
          "\t\t\t+ \" to topic\"",
          "\t\t\t+ 42",
          "\t\t\t+ \" so that no-one had a chance to\"",
          "\t\t\t+ \" interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s ="
              + " \"onelongincrediblyunbrokensentencemovingfromtopictotopicsothatnoonehadachanceto"
              + " interrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t"
              + "\"onelongincrediblyunbrokensentencemovingfromtopictotopicsothatnoonehadachanceto\"",
          "\t\t\t+ \" interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s = \"\\n\\none\\nlong\\nincredibly\\nunbroken\\nsentence\\nmoving\\nfrom\\n"
              + " topic\\nto\\n topic\\nso\\nthat\\nno-one\\nhad\\na\\nchance\\nto\\ninterrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"\\n\\n\"",
          "\t\t\t+ \"one\\n\"",
          "\t\t\t+ \"long\\n\"",
          "\t\t\t+ \"incredibly\\n\"",
          "\t\t\t+ \"unbroken\\n\"",
          "\t\t\t+ \"sentence\\n\"",
          "\t\t\t+ \"moving\\n\"",
          "\t\t\t+ \"from\\n\"",
          "\t\t\t+ \" topic\\n\"",
          "\t\t\t+ \"to\\n\"",
          "\t\t\t+ \" topic\\n\"",
          "\t\t\t+ \"so\\n\"",
          "\t\t\t+ \"that\\n\"",
          "\t\t\t+ \"no-one\\n\"",
          "\t\t\t+ \"had\\n\"",
          "\t\t\t+ \"a\\n\"",
          "\t\t\t+ \"chance\\n\"",
          "\t\t\t+ \"to\\n\"",
          "\t\t\t+ \"interrupt\";",
          "}",
        },
      },
      {
        {
          "class T {", //
          "  String s = \"\\n\\n\\none\\n\\nlong\\n\\nincredibly\\n\\nunbroken\\n\\nsentence\\n\\n"
              + "moving\\n\\nfrom\\n\\n topic\\n\\nto\\n\\n topic\\n\\nso\\n\\nthat\\n\\nno-one"
              + "\\n\\nhad\\n\\na\\n\\nchance\\n\\nto\\n\\ninterrupt\\n\\n\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"\\n\\n\\n\"",
          "\t\t\t+ \"one\\n\\n\"",
          "\t\t\t+ \"long\\n\\n\"",
          "\t\t\t+ \"incredibly\\n\\n\"",
          "\t\t\t+ \"unbroken\\n\\n\"",
          "\t\t\t+ \"sentence\\n\\n\"",
          "\t\t\t+ \"moving\\n\\n\"",
          "\t\t\t+ \"from\\n\\n\"",
          "\t\t\t+ \" topic\\n\\n\"",
          "\t\t\t+ \"to\\n\\n\"",
          "\t\t\t+ \" topic\\n\\n\"",
          "\t\t\t+ \"so\\n\\n\"",
          "\t\t\t+ \"that\\n\\n\"",
          "\t\t\t+ \"no-one\\n\\n\"",
          "\t\t\t+ \"had\\n\\n\"",
          "\t\t\t+ \"a\\n\\n\"",
          "\t\t\t+ \"chance\\n\\n\"",
          "\t\t\t+ \"to\\n\\n\"",
          "\t\t\t+ \"interrupt\\n\\n\";",
          "}",
        },
      },
      {
        {
          "class T {", //
          "  String s = \"onelongincrediblyunbrokensenten\\tcemovingfromtopictotopicsothatnoonehada"
              + "chance tointerrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"onelongincrediblyunbrokensenten\"",
          "\t\t\t+ \"\\tcemovingfromtopictotopicsothatnoonehadachance\"",
          "\t\t\t+ \" tointerrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s = \"onelongincrediblyunbrokensentencemovingfromtopictotopicsothatnoonehada"
              + "chancetointerrupt_____________________)_\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"onelongincrediblyunbrokensentencemovingfromtopictotopicsothatnoonehada"
              + "chancetointerrupt_____________________)_\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s = \"onelongincrediblyunbrokensentencemovingfromtopictotopicsot atnoonehada"
              + "chancetointerrupt______________________\";;",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"onelongincrediblyunbrokensentencemovingfromtopictotopicsot\"",
          "\t\t\t+ \" atnoonehadachancetointerrupt______________________\";",
          "\t;",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s = \"__ onelongincrediblyunbrokensentencemovingfromtopictotopicsothatnoonehada"
              + "chanceto interrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"__"
              + " onelongincrediblyunbrokensentencemovingfromtopictotopicsothatnoonehadachanceto\"",
          "\t\t\t+ \" interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s =",
          "      \"one long incredibly unbroken sentence\"",
          "          // comment",
          "          + \" moving from topic to topic\"",
          "          // comment",
          "          + \" so that no-one had a chance to\"",
          "          // comment",
          "          + \" interrupt\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"one long incredibly unbroken\"",
          "\t\t\t+ \" sentence\"",
          "\t\t\t// comment",
          "\t\t\t+ \" moving from topic to topic\"",
          "\t\t\t// comment",
          "\t\t\t+ \" so that no-one had a chance to\"",
          "\t\t\t// comment",
          "\t\t\t+ \" interrupt\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s =",
          "      \"aaaaaaaaaaaaaaaaaaaaaaaa bbbbbbbbbbbbbbbbbb ccccccccccccccccccccccccccc"
              + " dddddddddddddddddd\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"aaaaaaaaaaaaaaaaaaaaaaaa\"",
          "\t\t\t+ \" bbbbbbbbbbbbbbbbbb\"",
          "\t\t\t+ \" ccccccccccccccccccccccccccc\"",
          "\t\t\t+ \" dddddddddddddddddd\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  String s =",
          "      \"aaaaaaaaaaaaaaaaaaaaaaaa \"",
          "          + \"bbbbbbbbbbbbbbbbbb \"",
          "          + \"ccccccccccccccccccccccccccc \"",
          "          + \"dddddddddddddddddd\";",
          "}"
        },
        {
          "class T {",
          "\tString s =",
          "\t\t\"aaaaaaaaaaaaaaaaaaaaaaaa \"",
          "\t\t\t+ \"bbbbbbbbbbbbbbbbbb \"",
          "\t\t\t+ \"ccccccccccccccccccccccccccc \"",
          "\t\t\t+ \"dddddddddddddddddd\";",
          "}",
        }
      },
      {
        {
          "class T {", //
          "  byte[] bytes =",
          "      \"one long incredibly unbroken sentence moving from topic to topic so that no-one"
              + " had a chance to interrupt\".getBytes();",
          "}"
        },
        {
          "class T {", //
          "\tbyte[] bytes =",
          "\t\t\"one long incredibly unbroken sentence moving from topic to topic so that no-one"
              + " had a chance to interrupt\"",
          "\t\t\t.getBytes();",
          "}"
        },
      },
    };
    return Arrays.stream(inputsAndOutputs)
        .map(
            inputAndOutput -> {
              assertThat(inputAndOutput).hasLength(2);
              return new String[] {
                Joiner.on('\n').join(inputAndOutput[0]) + '\n', //
                Joiner.on('\n').join(inputAndOutput[1]) + '\n',
              };
            })
        .collect(toImmutableList());
  }

  private final Formatter formatter = new Formatter();

  private final String input;
  private final String output;

  public StringWrapperIntegrationTest(String input, String output) {
    this.input = input;
    this.output = output;
  }

  @Test
  public void test() throws Exception {
    assertThat(StringWrapper.wrap(40, formatter.formatSource(input), formatter)).isEqualTo(output);
  }

  @Test
  public void testCR() throws Exception {
    assertThat(StringWrapper.wrap(40, formatter.formatSource(input.replace("\n", "\r")), formatter))
        .isEqualTo(output.replace("\n", "\r"));
  }

  @Test
  public void testCRLF() throws Exception {
    assertThat(
            StringWrapper.wrap(40, formatter.formatSource(input.replace("\n", "\r\n")), formatter))
        .isEqualTo(output.replace("\n", "\r\n"));
  }

  @Test
  public void idempotent() throws Exception {
    String wrap = StringWrapper.wrap(40, formatter.formatSource(input), formatter);
    assertThat(wrap).isEqualTo(formatter.formatSource(wrap));
  }
}

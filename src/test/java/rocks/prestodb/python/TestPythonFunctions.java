/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rocks.prestodb.python;

import com.facebook.presto.operator.scalar.FunctionAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.facebook.presto.spi.type.VarcharType.createUnboundedVarcharType;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public class TestPythonFunctions
{
    private FunctionAssertions functionAssertions;

    @BeforeClass
    public void setUp()
    {
        functionAssertions = new FunctionAssertions().addScalarFunctions(PythonScalarFunctions.class);
    }

    @Test
    public void testPyScalar()
    {
        assertPyScalar("print 2", asList(), "2");

        assertPyScalar(
                "" +
                        "import sys\n" +
                        "print \\' \\'.join(sys.argv)",
                asList("ala", "ma", "kota"),
                "ala ma kota");
    }

    private void assertPyScalar(String pythonCode, List<String> arguments, String expected)
    {
        String argumentsString = arguments.stream()
                .map(arg -> "'" + arg + "'")
                .collect(joining(", "));
        argumentsString = "[" + argumentsString + "]";

        String projection = "py_scalar('" + pythonCode + "', ARRAY " + argumentsString + ")";
        functionAssertions.assertFunction(projection, createUnboundedVarcharType(), expected);
    }
}

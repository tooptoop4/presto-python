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

import com.facebook.presto.operator.Description;
import com.facebook.presto.operator.scalar.annotations.ScalarFunction;
import com.facebook.presto.spi.block.Block;
import com.facebook.presto.spi.type.StandardTypes;
import com.facebook.presto.spi.type.VarcharType;
import com.facebook.presto.type.LiteralParameters;
import com.facebook.presto.type.SqlType;
import io.airlift.slice.Slice;

public final class PythonScalarFunctions
{
    private PythonScalarFunctions()
    {
    }

    @Description("Executes a python script and passes given array as its arguments")
    @SqlType(VarcharType.VARCHAR_MAX_LENGTH)
    @LiteralParameters({"x", "y"})
    @ScalarFunction("py_scalar")
    public static Slice pyScalar(@SqlType("varchar(x)") Slice code, @SqlType("array(varchar(y))") Block arguments)
    {
        return null;
        Process.e
    }
}

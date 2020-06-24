/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.vishniya.simplecalc;



import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     * you can't test private methods btw !
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    // the d stands for double

    @Test
    public void addTwoNumbersNegative(){
        double resultAdd = mCalculator.add(-1d,2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }


    // closeTo allows you to give some wiggle room for precision -e.g. 0.01 either way is fine

    @Test
    public void addTwoNumbersFloat(){
        double resultAdd = mCalculator.add(2.5555f,3.32d);
        assertThat(resultAdd,is(closeTo(5.8755,0.01)));
    }

    @Test
    public void subTwoNumbers(){
        double resultSub = mCalculator.sub(3d,2d);
        assertThat(resultSub, is(equalTo(1d)));
    }


    @Test
    public void subWithNgativeResults(){
        double resultSub = mCalculator.sub(2d,3d);
        assertThat(resultSub, is(equalTo(-1d)));
    }



    @Test
    public void mulTwoNumbers(){
        double resultMul = mCalculator.mul(3d,2d);
        assertThat(resultMul, is(equalTo(6d)));
    }

    @Test
    public void mulTwoNumbersZero(){
        double resultMul = mCalculator.mul(0d,2d);
        assertThat(resultMul, is(equalTo(0d)));
    }
    @Test
    public void divByZeroThrows(){
        double resultDiv = mCalculator.div(3d, 0d);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void powWithPositiveIntegers(){
        double resultPow = mCalculator.pow(3d, 3d);
        assertThat(resultPow,is(equalTo(27d)));
    }

    @Test
    public void powWithNegFirstOperand(){
        double resultPow = mCalculator.pow(-3d, 3d);
        assertThat(resultPow,is(equalTo(-27d)));
    }

    @Test
    public void powWithNegSecondOperand(){
        double resultPow = mCalculator.pow(3d, -3d);
        assertThat(resultPow,is(equalTo(1/27d)));
    }

    @Test
    public void powWithZeroFirst(){
        double resultPow = mCalculator.pow(0d, 3d);
        assertThat(resultPow,is(equalTo(0d)));
    }

    @Test
    public void powWithZeroSecond(){
        double resultPow = mCalculator.pow(3d, 0d);
        assertThat(resultPow,is(equalTo(1d)));
    }

    // 0 to a neg power implies division by zero wh ich is undefined

    @Test
    public void powWithZeroFirstNegOneSecond(){
        double resultPow = mCalculator.pow(0d, -1d);
        assertThat(resultPow,is(equalTo(0d)));
    }

    @Test
    public void powWithZeroFirstNegSecond(){
        double resultPow = mCalculator.pow(0d, -3d);
        assertThat(resultPow,is(equalTo(0d)));
    }


}
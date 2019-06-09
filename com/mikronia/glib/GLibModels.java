/*
 * MIT License
 * 
 * Copyright (c) 2019 Thaynan Silva
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mikronia.glib;

import java.util.HashMap;

import com.mikronia.glib.utils.glib.GLibChecks;

/**
 * MkModels
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public class GLibModels {

	private static HashMap<String, GLibModel> models = new HashMap<String, GLibModel>();

	public static void store(String ref, GLibModel model) {
		GLibChecks.assertNotNull(ref, "model_reference_null");
		GLibChecks.assertFalse(ref.trim().isEmpty(), "model_reference_blank");
		
		GLibChecks.assertNotNull(model, "model_object_null");
		
		GLibChecks.assertFalse(models.containsKey(ref), "model_reference_exists");
		
		models.put(ref, model);
	}

	public static GLibModel model(String ref) {
		GLibChecks.assertNotNull(ref, "model_reference_null");
		GLibChecks.assertFalse(ref.trim().isEmpty(), "model_reference_blank");
		
		GLibChecks.assertTrue(models.containsKey(ref), "model_not_exists");
		
		return models.get(ref);
	}
}

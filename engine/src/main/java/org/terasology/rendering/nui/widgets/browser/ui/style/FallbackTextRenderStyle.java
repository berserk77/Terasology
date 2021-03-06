/*
 * Copyright 2015 MovingBlocks
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
package org.terasology.rendering.nui.widgets.browser.ui.style;

import org.terasology.nui.asset.font.Font;
import org.terasology.nui.Color;

public class FallbackTextRenderStyle implements TextRenderStyle {
    private TextRenderStyle style;
    private TextRenderStyle fallback;

    public FallbackTextRenderStyle(TextRenderStyle style, TextRenderStyle fallback) {
        this.style = style;
        this.fallback = fallback;
    }

    @Override
    public Font getFont(boolean hyperlink) {
        Font font = style.getFont(hyperlink);
        if (font == null) {
            font = fallback.getFont(hyperlink);
        }
        return font;
    }

    @Override
    public Color getColor(boolean hyperlink) {
        Color color = style.getColor(hyperlink);
        if (color == null) {
            color = fallback.getColor(hyperlink);
        }
        return color;
    }
}

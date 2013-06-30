/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *
 * @author silvio
 */
public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
        int textLength = this.getText().length();
        int lines = textLength / 5 + 1;//+1, cause we need at least 1 row.           
        int height = fontHeight * lines;
        table.setRowHeight(row, height);
        this.setText((String) value);
        this.setWrapStyleWord(true);
        this.setLineWrap(true);

        return this;
    }
}

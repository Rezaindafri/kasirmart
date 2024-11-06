package Custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JTable_Custom extends JTable{
    
    private int selectedRow = -1;
    public JTable_Custom() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setForeground(new Color(0,0,0));
        setRowHeight(45);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean blnl, int i, int il) {
                TablezHeader header = new TablezHeader(o + ""); 
                if (il==10) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
                com.setBackground(Color.WHITE);
                
                if (isSelected) {
                    com.setForeground(new Color(0,0,0));
                    com.setBackground(new Color(122,186,120));
                    com.setFont(new Font("Myanmar Text",1,18));
                } else {
                    com.setForeground(new Color(0,0,0));
                    com.setFont(new Font("Myanmar Text",0,18));
                }
                return com;
            } 
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                if (row == selectedRow) {
                    clearSelection();
                    selectedRow = -1;
                } else {
                    selectedRow = row;
                }
            }
        });
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
    
    private class TablezHeader extends JLabel{
        
        public TablezHeader(String text) {
            super(text);
            setOpaque(true);
            setBackground(new Color(10,104,71));
            setFont(new Font("sansserif",1,18));
            setForeground(new Color(255,255,255));
            setBorder(new EmptyBorder(10,5,10,5));
        }

        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            g.setColor(new Color(230,230,230));
            g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
        
        
        
    }
}

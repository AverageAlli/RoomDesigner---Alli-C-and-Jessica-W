package edu.bsu.cs;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;
    private int roomSize;
    private JLabel furnitureLabel;
    private JComboBox<String> furnitureComboBox;
    private Map<String, BufferedImage> furnitureImages;
    private DragAndDropHandler handler;

    public BedroomDesignerGUI(int roomSize) {
        this.roomSize = roomSize;
        setTitle("Room Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

        initComponents();
        furnitureImages = loadFurnitureImages();
        //handler = new DragAndDropHandler(roomPanel);
        setVisible(true);
    }

    private void initComponents() {
        roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawRoom(g); // Draw the room outline
            }
        };
        roomPanel.setBackground(Color.WHITE);
        roomPanel.setPreferredSize(new Dimension(400, 400));
        roomPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // Add drag and drop functionality to the roomPanel
        //new DragAndDropHandler(roomPanel);
        roomPanel.setTransferHandler(new ImageTransferHandler());
        roomPanel.setDropTarget(new DropTarget());
        //new DropTarget(roomPanel, DnDConstants.ACTION_COPY, new MyDropTargetListener());

        String[] furnitureOptions = {"Select Furniture", "Bed", "Dresser", "Nightstand", "Chair"};
        furnitureComboBox = new JComboBox<>(furnitureOptions);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(furnitureComboBox);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(roomPanel, BorderLayout.CENTER);

        furnitureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFurniture = (String) furnitureComboBox.getSelectedItem();
                if (!selectedFurniture.equals("Select Furniture")) {
                    displayFurnitureImage(selectedFurniture);
                }
            }
        });
    }

            private Map<String, BufferedImage> loadFurnitureImages() {
                Map<String, BufferedImage> images = new HashMap<>();
                // Load images for each furniture type and store in the map
                try {
                    images.put("Bed", ImageIO.read(new File("src/main/ObjectImages/bed.png")));
                    images.put("Dresser", ImageIO.read(new File("src/main/ObjectImages/dresser.png")));
                    images.put("Chair", ImageIO.read(new File("src/main/ObjectImages/chair.png")));
                    images.put("Nightstand", ImageIO.read(new File("src/main/ObjectImages/nightstand.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return images;
            }

            private void displayFurnitureImage(String furnitureName) {
                BufferedImage image = furnitureImages.get(furnitureName);
                if (image != null) {
                    ImageIcon icon = new ImageIcon(image);
                    JLabel label = new JLabel(icon);
                    label.setBorder(new LineBorder(Color.BLACK));
                    label.setTransferHandler(new ImageTransferHandler());
                    label.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (SwingUtilities.isRightMouseButton(e)) {
                                // Remove the label from the roomPanel
                                roomPanel.remove(label);
                                roomPanel.revalidate();
                                roomPanel.repaint();
                            } else {
                                JComponent comp = (JComponent) e.getSource();
                                TransferHandler handler = comp.getTransferHandler();
                                handler.exportAsDrag(comp, e, TransferHandler.COPY);
                            }
                        }
                    });


//                    label.addMouseMotionListener(new MouseMotionAdapter() {
//                        @Override
//                        public void mouseDragged(MouseEvent e) {
//                            handler.mouseDragged(e);
//                        }
//                    });

                    roomPanel.add(label);
                    roomPanel.revalidate();
                    roomPanel.repaint();
                }
            }



            private void drawRoom(Graphics graphics) {
                int width = roomPanel.getWidth();
                int height = roomPanel.getHeight();

                // Clear the panel before drawing
                graphics.clearRect(0, 0, width, height);

                // Draw the outline of the room based on the selected room size
                graphics.setColor(Color.BLACK);
                switch (roomSize) {
                    case 1: // 9x16
                        graphics.drawRect(width / 12, height / 12, width * 4 / 6, height * 4 / 6);
                        break;
                    case 2: // 10x8
                        graphics.drawRect(width / 8, height / 8, width * 3 / 4, height * 3 / 4);
                        break;
                    case 3: // 10x12
                        graphics.drawRect(width / 8, height / 6, width * 3 / 4, height * 4 / 6);
                        break;
                    case 4: // 10x14
                        graphics.drawRect(width / 8, height / 6, width * 3 / 4, height * 5 / 6);
                        break;
                    case 5: // 12x8
                        graphics.drawRect(width / 12, height / 4, width * 4 / 6, height / 2);
                        break;
                    case 6: // 12x12
                        graphics.drawRect(width / 12, height / 12, width * 4 / 6, height * 4 / 6);
                        break;
                    default:
                        break;
                }
            }

    // Inner class implementing DropTargetListener
    private class MyDropTargetListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable transferable = dtde.getTransferable();
                if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    BufferedImage image = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel label = new JLabel(icon);
                    label.setBorder(new LineBorder(Color.BLACK)); // Add border for better visualization
                    roomPanel.add(label);
                    roomPanel.revalidate();
                    roomPanel.repaint();
                    dtde.dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                dtde.rejectDrop();
            }
        }
    }
    private class ImageTransferHandler extends TransferHandler {
        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            JLabel label = (JLabel) c;
            return new Transferable() {
                @Override
                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[]{DataFlavor.imageFlavor};
                }

                @Override
                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return flavor.equals(DataFlavor.imageFlavor);
                }

                @Override
                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                    return label.getIcon();
                }
            };
        }
    }
            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(0)); // Placeholder value for room size
            }
        }


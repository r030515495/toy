package org.rex.tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 井字遊戲 UI
 */
public class GameUI implements ActionListener {
	private static JFrame frame = new JFrame();
	private static ArrayList<JButton> btns = new ArrayList<>();
	private static List<String> winRegxs = Arrays.asList("012", "345", "678", "036", "147", "258", "048", "246");
	private static Integer times = 0;

	public GameUI() {
		frame.setSize(450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout experimentLayout = new GridLayout(0, 3);
		frame.getContentPane().setLayout(experimentLayout);

	}

	/**
	 * 執行
	 */
	public void run() {
		for (int i = 0; i < 9; i++) {
			JButton btn = new JButton();
			btn.addActionListener(this);
			btns.add(btn);
			frame.getContentPane().add(btn);
		}
		reset();
		frame.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (isEmpty(b.getText())) {
			if (times == 0) {
				times = 1;
				b.setText("O");
				frame.setTitle("井字遊戲，輪到X");
			} else {
				times = 0;
				b.setText("X");
				frame.setTitle("井字遊戲，輪到O");
			}
			if (checkWin()) {
				JOptionPane.showMessageDialog(frame, frame.getTitle(), "遊戲結束", JOptionPane.INFORMATION_MESSAGE);
				reset();
			}
		}
	}

	/**
	 * 判斷結果
	 * 
	 * @return
	 */
	private Boolean checkWin() {
		String Ostring = "";
		String Xstring = "";
		for (int i = 0; i < btns.size(); i++) {
			JButton jButton = btns.get(i);
			String text = jButton.getText();
			if ("O".equals(text)) {
				Ostring += "" + i;
			} else if ("X".equals(text)) {
				Xstring += "" + i;
			}
		}

		for (String winRegx : winRegxs) {
			int matchCountX = 0;
			int matchCountO = 0;
			String[] nums = winRegx.split("");
			for (String num : nums) {
				if (Ostring.contains(num)) {
					matchCountO++;
				}
				if (Xstring.contains(num)) {
					matchCountX++;
				}

			}
			if (matchCountO == 3) {
				frame.setTitle("井字遊戲，恭喜 O 贏了！！");
				return true;
			}
			if (matchCountX == 3) {
				frame.setTitle("井字遊戲，恭喜 X 贏了！！");
				return true;
			}

			if (Ostring.length() + Xstring.length() == 9) {
				frame.setTitle("井字遊戲，此次平手！！");
				return true;
			}
		}
		return false;
	}

	/**
	 * 重置
	 */
	private void reset() {
		times = 0;
		frame.setTitle("井字遊戲，由O先開始");
		for (JButton jButton : btns) {
			jButton.setText("");
		}
	}

	/**
	 * 檢查文字是否為空
	 * 
	 * @param str
	 *            檢查文字
	 * @return
	 */
	private Boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;

	}

}

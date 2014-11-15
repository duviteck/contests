package cscenter.year_2013_2014;

import java.util.Scanner;

public class Advanced_Problem_3D {

    private static char[] buffer;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        s.nextLine();
        String line = s.nextLine();

        buffer = line.toCharArray();
        char[] resultBuffer = processBuffer(0, buffer.length, k);
        System.out.println(new String(resultBuffer));
    }

    private static char[] processBuffer(int startPos, int endPos, int resultSize) {
        if (resultSize == 0) {
            return new char[0];
        }

        // Handle border case
        if (resultSize == buffer.length) {
            return buffer;
        }

        // Handle another border case
        char[] resultBuffer = new char[resultSize];
        if (resultSize == endPos - startPos) {
            System.arraycopy(buffer, startPos, resultBuffer, 0, resultSize);
            return resultBuffer;
        }

        // Process common case
        int resultBufferIndex = 0;
        int throwOutSize = endPos - startPos - resultSize;

        // Find max value
        char maxChar = buffer[startPos];
        for (int i = startPos + 1; i < endPos; i++) {
            if (buffer[i] > maxChar) {
                maxChar = buffer[i];
            }
        }

        // Remove elements between max values
        int lastStart = startPos;
        for (int i = startPos; i < endPos; i++) {
            if (buffer[i] != maxChar) {
                continue;
            }

            if (i - lastStart <= throwOutSize) {
                throwOutSize -= i - lastStart;
                resultBuffer[resultBufferIndex++] = maxChar;
                lastStart = i + 1;
                if (resultBufferIndex == resultBuffer.length) {
                    break;
                }
            } else {
                char[] tempBuffer = processBuffer(lastStart, i, i - lastStart - throwOutSize);
                System.arraycopy(tempBuffer, 0, resultBuffer, resultBufferIndex, tempBuffer.length);
                resultBufferIndex += tempBuffer.length;
                System.arraycopy(buffer, i, resultBuffer, resultBufferIndex, endPos - i);
                resultBufferIndex += endPos - i;
                break;
            }
        }

        if (resultBufferIndex != resultBuffer.length) {
            char[] tempBuffer = processBuffer(lastStart, endPos, resultBuffer.length - resultBufferIndex);
            System.arraycopy(tempBuffer, 0, resultBuffer, resultBufferIndex, tempBuffer.length);
        }

        return resultBuffer;
    }
}

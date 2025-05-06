%Plotter function, just used a simple function.
x = -20:0.25:20;
y = x.^2 + 2;

clf; %clearing graph to use multiple times.
hold on; %holding so all three graphs can show at once.

%plotting the plotter with blue color, 2 thickness
plot(x, y, 'b-', 'DisplayName', 'Original Data', 'LineWidth', 1.5);


%loop to salt the data comparatively to the java file.
for i = 1:length(y)
  temp = y(i);
  temp = temp + (rand() * 200 - 100);
  y(i) = temp;
endfor;



%setting the window size and smoothing with a moving average.
winSize = 10;
smoother = movmean(y, winSize);

%plotting the salted and smoothed graphs.
%salted is purple with thickness 2.
%smoothed is red with thickness 2.
plot(x, y, 'Color', '#800080', 'LineWidth', 1.5, 'DisplayName', 'Salted Data');
plot(x, smoother, 'r-', 'LineWidth', 1.5, 'DisplayName', 'Smoothed Data');
legend;
title('Plotted, Salted and Smoothed Data from function y = x^2 + 2');
xlabel('Index');
ylabel('Y Values');
hold off; %stop the hold on graph display.


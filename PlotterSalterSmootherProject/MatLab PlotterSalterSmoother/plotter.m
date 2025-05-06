%Plotter function, just used a simple function.
x = -20:0.25:20;
y = x.^2 + 2;

clf; %clearing graph to use multiple times.
hold on; %holding so all three graphs can show at once.

%plotting the plotter with blue color, 2 thickness
plot(x, y, 'b-', 'LineWidth', 2);


%loop to salt the data comparatively to the java file.
for i = 1:length(y)
  temp = y(i);
  temp = temp + (rand() * 200 - 100);
  y(i) = temp;
end;



%setting the window size and smoothing with a moving average.
winSize = 10;
smoother = movmean(y, winSize);

%plotting the salted and smoothed graphs.
%salted is purple with thickness 2.
%smoothed is red with thickness 2.
plot(x, y, 'Color', '#800080', 'LineWidth', 2);
plot(x, smoother, 'r-', 'LineWidth', 2);
hold off; %stop the hold on graph display.


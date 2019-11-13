#  exercise 1,5,11
arr = []
puts "Enter element = "
begin
  ele = gets.chomp.to_i
  if ele !=-1
    arr.push ele
  end
end while ele != -1
puts "Arr: "
arr.sort.reverse!.each {|e| print e, " "}

# exercise 2
arr = [1, 3, 5, 8, 12]
print "Avg = ",arr.reduce(:+).to_f/arr.length, "\n"

# exercise 3,4
arr = [1, 3, 5, 8, 1, 5 , 6, 8, 2, 3]
puts "x: "
x = gets.chomp.to_i
printf "Times = %d\n",arr.count(x)

# exercise 6
arr = [1, 3, 5, 8, 1, 5, 6, 8, 2, 3]
puts "k: "
k = gets.chomp.to_i
if k >= arr.size
  puts "k > arr size"
else
  arr.delete_at k
end
arr.each {|e| print e, " "}

# exercise 7
arr = [1, 3, 5, 8, 1, 5, 6, 8, 2, 3]
puts "x: "
x = gets.chomp.to_i
printf "Delete all: %d\n",arr.count(x)
arr.delete x
arr.each {|e| print e, " "}

# exercise 8
arr = [1, 3, 5, 8, 1, 5, 6, 8, 2, 3]
puts "x, k: "
x = gets.chomp.to_i
k = gets.chomp.to_i
if k >= arr.size
  puts "k > arr size"
else
  arr.insert k, x
  arr.each {|e| print e, " "}
end

#  exercise 9
arr = [1, 3, 5, 8, 1, 5, 6, 8, 2, 3]
puts "x: "
x = gets.chomp.to_i
arr.insert x
arr.sort.each {|e| print e, " "}

# exercise 10
arr = [1.4, 3.7, 5.4, 8.1, 1.5, 5.6, 6.7, 8, 2.2, 3.9]
puts "x: "
x = gets.chomp.to_f
puts arr.select {|i| i <= x }

# exercise 12
arr = [[1, 6, 5], [8, 1, 5], [6, 8, 2, 3]]
arr.each do |e| e.sort.each {|i| printf " %d ", i}
  puts
end



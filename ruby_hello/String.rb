# exercise 1
puts "Enter string: "
str = gets.chomp
puts str.reverse

# exercise 2
puts "Enter string: "
str = gets.chomp
puts str.split " "

# exercise 3
arr = []
puts "Enter element = "
begin
  ele = gets.chomp.to_i
  if ele !=-1
    arr.push ele
  end
end while ele != -1
arr.each do |i|
  case i
  when 1
    puts "One"
  when 2
    puts "Two"
  when 3
    puts "Three"
  when 4
    puts "Four"
  when 5
    puts "Five"
  when 6
    puts "Six"
  when 7
    puts "Seven"
  when 8
    puts "Eight"
  when 9
    puts "Nine"
  end
end

#  exercise 4
puts "Enter string: "
str = gets.chomp
puts str.count "/\s+/"

# exercise 5
str = "yyyHHHaf983haG871"
printf "Uppercase %d \n", str.scan(/[A-Z]/).length
printf "Lowercase %d \n", str.scan(/[a-z]/).length
printf "Number %d \n", str.scan(/[0-9]/).length

# exercise 6
str = " yyyHHHaf983haG871   "
puts str.strip

# exercise 7
def countCharacter(str)
  str.chars.group_by{|c| c}.map { |k, v| printf "%s : %d, ", k, v.size }
end
countCharacter "somehting ehe "

# exercise 8
str = " yyyHHH af983h aG871   "
puts "n, k: "
n = gets.chomp.to_i
k = gets.chomp.to_i
if k >= str.length
  puts "k > string length"
else
  str[k,n] = ""
  puts str
end

#  exercise 9
str = " yyyHHH af983h aG871   "
str2 = "yaha7H"
puts str.delete str2

#  exercise 10
def formatName(str)
 puts str.split(/\s+/).map { |e|
   e[0].upcase + e[1, e.length-1].downcase
 }
end
formatName "huahua huahd Ha"

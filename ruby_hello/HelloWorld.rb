puts "hello, ruby"

BEGIN {
  # declare code to be called before a program runs
  puts "component here"
}

END {
  # called after a program runs
  puts "after program runned"
}

=begin
block comment here
=end
$num = 5
NUM = 6
class Customer
#   local variable _nam
# instance variable @name
# class variable @@name
# global variable $name - across classes
  @@noCustomer = 0
  # array in ruby
  arr = ["hello", 7, "ioo"]
  arr.each { |i| puts i }
  # map in ruby
  mapHash = Hash.new("one" => 1, "two" => 2)
  mapRu = {"one" => 1, "two" => 2}
  mapRu.each do |key, value|
    print key, "-", value
  end
  # ruby range
  (10..12).each { |i| puts i }
  # range from 1 to 10 1..10
  # range from 1 to 9 1...9
  def initialize(id, addr, name)
    @id = id
    @addr = addr
    @name = name
  end

  def hello(name)
    print "hello hihi #{NUM} ", name, "/n"
  end

  # if is false
  unless 1 > 2
    puts "1>2 false"
  else
    puts "1>2 true"
  end
end

print "Haha" unless false
# case in ruby
$age = 8
case $age
when 0..5
  puts "baby"
else
  puts "adult"
end
while 1!=1
  puts "somehit"
end
# do while ruby
begin
  num = 1
end while num <10
# while with condition is false
until 1==0

end
# do while with condition is false
begin

end until 1!=0
# for loop ruby
i = 0
for i in 1..0
  if i ===1
    # redo retry
    next
    break
  end
end
u1 = Customer.new(1, "addres 1", "john")
u2 = Customer.new(2, "res 2", "Jak")
u1.hello "my"

# block in ruby
def test
  yield 3
end
test { |i| print "Hello ",i}

# module in ruby contains block of classes, methods, constants
str = String.new("ahahahHAHHAHAH")
puts str.downcase
# array ruby
digits = Array.new(20) {|e| e *= 2}
digits = digits.reject { |e| e > 20 }
puts digits.each { |e| e }
